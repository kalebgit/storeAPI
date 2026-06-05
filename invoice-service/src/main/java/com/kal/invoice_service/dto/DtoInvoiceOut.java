package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Factura generada tras finalizar la compra")
public class DtoInvoiceOut {

    @Schema(description = "ID de la factura", example = "10")
    private Integer id;

    @Schema(description = "ID del cliente", example = "1")
    private Integer customerId;

    @Schema(description = "Subtotal sin impuestos", example = "35.28")
    private Float subtotal;

    @Schema(description = "Impuestos (16%)", example = "6.72")
    private Float taxes;

    @Schema(description = "Total con impuestos incluidos", example = "42.00")
    private Float total;

    @Schema(description = "Fecha y hora de creación", example = "2026-06-05T18:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Productos incluidos en la factura")
    private List<DtoInvoiceItemOut> items;
}
