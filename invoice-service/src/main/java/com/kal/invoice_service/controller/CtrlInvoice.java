package com.kal.invoice_service.controller;

import com.kal.invoice_service.dto.DtoInvoiceOut;
import com.kal.invoice_service.service.SvcInvoice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
@Tag(name = "Facturación", description = "Finalización de compra y generación de facturas")
public class CtrlInvoice {

    private final SvcInvoice svcInvoice;

    @Operation(summary = "Finalizar compra y generar factura")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Factura generada exitosamente"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente para algún producto"),
            @ApiResponse(responseCode = "400", description = "El carrito está vacío"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping
    public ResponseEntity<DtoInvoiceOut> create(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(svcInvoice.create(Integer.parseInt(userId)));
    }
}
