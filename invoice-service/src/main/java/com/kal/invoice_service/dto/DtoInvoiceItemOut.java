package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Línea de producto dentro de una factura")
public class DtoInvoiceItemOut {

    @Schema(description = "ID del item", example = "1")
    private Integer id;

    @Schema(description = "ID del producto", example = "3")
    private Integer productId;

    @Schema(description = "Nombre del producto al momento de la compra", example = "Coca-cola 600 ml")
    private String productName;

    @Schema(description = "Precio unitario al momento de la compra", example = "21.00")
    private Float unitPrice;

    @Schema(description = "Cantidad comprada", example = "2")
    private Integer quantity;

    @Schema(description = "Subtotal de este producto (unitPrice × quantity)", example = "42.00")
    private Float subtotal;
}
