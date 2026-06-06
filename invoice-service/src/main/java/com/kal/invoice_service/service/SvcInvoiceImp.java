package com.kal.invoice_service.service;

import com.kal.invoice_service.dto.DtoInvoiceIn;
import com.kal.invoice_service.dto.DtoInvoiceOut;
import com.kal.invoice_service.dto.DtoPaymentInfoOut;
import com.kal.invoice_service.dto.DtoShippingAddressOut;
import com.kal.invoice_service.dto.DtoProductOut;
import com.kal.invoice_service.entity.*;
import com.kal.invoice_service.exception.ApiException;
import com.kal.invoice_service.feign.ProductClient;
import com.kal.invoice_service.mapper.MapperInvoice;
import com.kal.invoice_service.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SvcInvoiceImp implements SvcInvoice {

    private final RepoInvoice repoInvoice;
    private final RepoInvoiceItem repoInvoiceItem;
    private final RepoCartItem repoCartItem;
    private final RepoShippingAddress repoShippingAddress;
    private final RepoPaymentInfo repoPaymentInfo;
    private final RepoCoupon repoCoupon;

    private final SvcCartItem svcCartItem;
    private final ProductClient productClient;
    private final MapperInvoice mapperInvoice;

    @Transactional
    @Override
    public DtoInvoiceOut create(Integer customerId, DtoInvoiceIn in) {

        // 1. Leer carrito
        List<CartItem> items = repoCartItem.getCartItemsByCustomerId(customerId);
        if (items.isEmpty()) {
            throw new ApiException("El carrito está vacío", HttpStatus.BAD_REQUEST);
        }

        // 2. Validar stock y calcular total
        List<InvoiceItem> itemsToSave = new ArrayList<>();
        float total = 0f;
        for (CartItem item : items) {
            DtoProductOut product = productClient.getProduct(item.getProductId());
            if (product.getStock() < item.getQuantity()) {
                throw new ApiException(
                        "Stock insuficiente para el producto: " + product.getName() +
                                ". Disponible: " + product.getStock() + ", solicitado: " + item.getQuantity(),
                        HttpStatus.CONFLICT
                );
            }
            float itemSubtotal = product.getPrice() * item.getQuantity();
            total += itemSubtotal;

            itemsToSave.add(InvoiceItem.builder()
                    .productId(item.getProductId())
                    .productName(product.getName())
                    .unitPrice(product.getPrice())
                    .quantity(item.getQuantity())
                    .subtotal(itemSubtotal)
                    .build());
        }

        // 3. Aplicar cupón si viene
        float discountAmount = 0f;
        if (in != null && in.getCouponCode() != null && !in.getCouponCode().isBlank()) {
            Coupon coupon = repoCoupon.findByCodeAndActiveTrue(in.getCouponCode())
                    .orElseThrow(() -> new ApiException("Cupón inválido o inactivo", HttpStatus.NOT_FOUND));
            discountAmount = total * coupon.getDiscount();
        }

        // 4. Calcular totales finales
        float totalAfterDiscount = total - discountAmount;
        float taxes = totalAfterDiscount * 0.16f;
        float subtotal = totalAfterDiscount - taxes;

        // 5. Guardar factura
        Invoice invoice = Invoice.builder()
                .customerId(customerId)
                .subtotal(subtotal)
                .taxes(taxes)
                .discount(discountAmount)
                .total(totalAfterDiscount)
                .build();
        Invoice savedInvoice = repoInvoice.save(invoice);

        // 6. Guardar items
        for (InvoiceItem invoiceItem : itemsToSave) {
            invoiceItem.setInvoice(savedInvoice);
            repoInvoiceItem.save(invoiceItem);
        }

        // 7. Guardar dirección de envío si viene
        DtoShippingAddressOut shippingOut = null;
        if (in != null && in.getStreet() != null) {
            ShippingAddress address = ShippingAddress.builder()
                    .street(in.getStreet())
                    .city(in.getCity())
                    .state(in.getState())
                    .zipCode(in.getZipCode())
                    .invoice(savedInvoice)
                    .build();
            repoShippingAddress.save(address);
            shippingOut = new DtoShippingAddressOut();
            shippingOut.setStreet(address.getStreet());
            shippingOut.setCity(address.getCity());
            shippingOut.setState(address.getState());
            shippingOut.setZipCode(address.getZipCode());
        }

        // 8. Guardar info de pago si viene
        DtoPaymentInfoOut paymentOut = null;
        if (in != null && in.getPaymentMethod() != null) {
            PaymentInfo payment = PaymentInfo.builder()
                    .paymentMethod(in.getPaymentMethod())
                    .cardLastFour(in.getCardLastFour())
                    .invoice(savedInvoice)
                    .build();
            repoPaymentInfo.save(payment);
            paymentOut = new DtoPaymentInfoOut();
            paymentOut.setPaymentMethod(payment.getPaymentMethod());
            paymentOut.setCardLastFour(payment.getCardLastFour());
        }

        // 9. Actualizar stock
        for (CartItem item : items) {
            productClient.updateStock(item.getProductId(), item.getQuantity());
        }

        // 10. Vaciar carrito
        svcCartItem.clearCart(customerId);

        // 11. Construir respuesta
        DtoInvoiceOut out = mapperInvoice.invoiceToDtoInvoiceOut(savedInvoice);
        out.setShippingAddress(shippingOut);
        out.setPaymentInfo(paymentOut);
        return out;
    }
}
