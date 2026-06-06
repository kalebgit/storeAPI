package com.kal.invoice_service.controller;

import com.kal.invoice_service.dto.DtoInvoiceIn;
import com.kal.invoice_service.dto.DtoInvoiceOut;
import com.kal.invoice_service.entity.Coupon;
import com.kal.invoice_service.repository.RepoCoupon;
import com.kal.invoice_service.service.SvcInvoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
@Tag(name = "Facturación", description = "Finalización de compra y generación de facturas")
public class CtrlInvoice {

    private final SvcInvoice svcInvoice;
    private final RepoCoupon repoCoupon;

    @Operation(
            summary = "Finalizar compra y generar factura",
            description = "Todos los campos del body son opcionales. " +
                    "Incluye dirección de envío, método de pago y/o cupón de descuento según se requiera."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Factura generada exitosamente"),
            @ApiResponse(responseCode = "400", description = "El carrito está vacío"),
            @ApiResponse(responseCode = "404", description = "Cupón inválido o inactivo"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente para algún producto"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping
    public ResponseEntity<DtoInvoiceOut> create(
            @RequestHeader("X-User-Id") String userId,
            @RequestBody(required = false) DtoInvoiceIn in) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(svcInvoice.create(Integer.parseInt(userId), in));
    }

    @Operation(summary = "Crear cupón de descuento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cupón creado"),
            @ApiResponse(responseCode = "409", description = "El código de cupón ya existe"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping("/coupon")
    public ResponseEntity<String> createCoupon(@RequestBody Coupon coupon) {
        coupon.setActive(true);
        repoCoupon.save(coupon);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cupón creado exitosamente");
    }
}
