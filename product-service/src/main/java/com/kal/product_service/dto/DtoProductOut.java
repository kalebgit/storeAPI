package com.kal.product_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos de un producto")
public class DtoProductOut {

    @Schema(description = "ID único del producto", example = "1")
    private Integer productId;

    @Schema(description = "Código GTIN", example = "7501055300057")
    private String gtin;

    @Schema(description = "Nombre del producto", example = "Coca-cola 600 ml")
    private String name;

    @Schema(description = "Descripción", example = "Refresco de cola 600 ml")
    private String description;

    @Schema(description = "Precio unitario", example = "21.00")
    private Float price;

    @Schema(description = "Unidades en inventario", example = "98")
    private Integer stock;

    @Schema(description = "ID de la categoría", example = "1")
    private Integer categoryId;
}
