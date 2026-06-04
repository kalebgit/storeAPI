package com.kal.invoice_service.service;

import com.kal.invoice_service.dto.DtoInvoiceOut;
import com.kal.invoice_service.dto.DtoProductOut;
import com.kal.invoice_service.entity.CartItem;
import com.kal.invoice_service.entity.Invoice;
import com.kal.invoice_service.entity.InvoiceItem;
import com.kal.invoice_service.exception.ApiException;
import com.kal.invoice_service.feign.CustomerClient;
import com.kal.invoice_service.feign.ProductClient;
import com.kal.invoice_service.mapper.MapperCartItem;
import com.kal.invoice_service.mapper.MapperInvoice;
import com.kal.invoice_service.repository.RepoCartItem;
import com.kal.invoice_service.repository.RepoInvoice;
import com.kal.invoice_service.repository.RepoInvoiceItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SvcInvoiceImp implements SvcInvoice{

    private final RepoInvoice repoInvoice;
    private final RepoInvoiceItem repoInvoiceItem;
    private final RepoCartItem repoCartItem;

    private final SvcCartItem svcCartItem;

    private final ProductClient productClient;

    private final MapperInvoice mapperInvoice;

    @Transactional
    @Override
    public DtoInvoiceOut create(Integer customerId) {
        //leer todos los cart items
        List<CartItem> items = repoCartItem.getCartItemsByCustomerId(customerId);

        List<InvoiceItem> itemsToSave = new ArrayList<>();
        float total = 0f;
        //validar stockkk (feign)
        for(CartItem item: items){
            DtoProductOut product = productClient.getProduct(item.getProductId());
           if(product.getStock() < item.getQuantity()){
               throw new ApiException(
                       "Stock insuficiente para el producto: " + product.getName() +
                               ". Disponible: " + product.getStock() + ", solicitado: " + item.getQuantity(),
                       HttpStatus.CONFLICT
               );
           }
            float itemSubtotal = product.getPrice() * item.getQuantity();
            total += itemSubtotal;  // acumulas



            itemsToSave.add(InvoiceItem.builder()
                    .productId(item.getProductId())
                    .productName(product.getName())
                    .unitPrice(product.getPrice())
                    .quantity(item.getQuantity())
                    .subtotal(itemSubtotal)
                    .build());

        }
        //calcular totales
        float totalTaxes    = total * 0.16f;
        float subtotal = total - totalTaxes;


        //persistir en invoice y a su vez en invoiceItems (es decir aqui se crean)
        Invoice invoice = Invoice.builder()
                .customerId(customerId)
                .subtotal(subtotal)
                .taxes(totalTaxes)
                .total(total)
                .build();

        Invoice createdInvoice = repoInvoice.save(invoice);

        for(InvoiceItem invoiceItem : itemsToSave){
            invoiceItem.setInvoice(invoice);
            repoInvoiceItem.save(invoiceItem);
        }

        //actualizar el stock
        for(CartItem item : items){
             productClient.updateStock(item.getProductId(), item.getQuantity());
        }
        //vaciar el carrito
        svcCartItem.clearCart(customerId);

        return mapperInvoice.invoiceToDtoInvoiceOut(createdInvoice);
    }
}
