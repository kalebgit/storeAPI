package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Producto a agregar al carrito")
public class DtoCartItemIn {

    @Schema(hidden = true)
    private Integer customerId;

    @Schema(description = "ID del producto a agregar", example = "1")
    private Integer productId;

    @Schema(description = "Cantidad de unidades", example = "2")
    private Integer quantity;
}
