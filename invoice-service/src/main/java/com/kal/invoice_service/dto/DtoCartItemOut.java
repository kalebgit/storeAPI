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
@Schema(description = "Artículo en el carrito de compras")
public class DtoCartItemOut {

    @Schema(description = "ID del artículo en el carrito", example = "5")
    private Integer id;

    @Schema(description = "ID del cliente", example = "1")
    private Integer customerId;

    @Schema(description = "ID del producto", example = "3")
    private Integer productId;

    @Schema(description = "Nombre del producto", example = "Coca-cola 600 ml")
    private String name;

    @Schema(description = "Precio unitario", example = "21.00")
    private Float price;

    @Schema(description = "Cantidad en el carrito", example = "2")
    private Integer quantity;
}
