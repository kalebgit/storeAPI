package com.kal.product_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "Datos para crear o actualizar un producto")
public class DtoProductIn {

    @Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
    @NotNull(message = "el gtin es obligatorio")
    @Schema(description = "Código GTIN de 13 dígitos", example = "7501055300057")
    private String gtin;

    @NotNull(message = "El product es obligatorio")
    @Schema(description = "Nombre del producto", example = "Coca-cola 600 ml")
    private String name;

    @NotNull(message = "El description es obligatorio")
    @Schema(description = "Descripción del producto", example = "Refresco de cola 600 ml")
    private String description;

    @Min(value = 0)
    @NotNull(message = "El price es obligatorio")
    @Schema(description = "Precio unitario", example = "21.00")
    private Float price;

    @NotNull(message = "El stock es obligatorio")
    @Schema(description = "Unidades disponibles en inventario", example = "100")
    private Integer stock;

    @NotNull(message = "El categoryId es obligatorio")
    @Schema(description = "ID de la categoría del producto", example = "1")
    private Integer categoryId;
}
