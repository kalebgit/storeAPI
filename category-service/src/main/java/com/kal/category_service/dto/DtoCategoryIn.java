package com.kal.category_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Datos para crear o actualizar una categoría")
public class DtoCategoryIn {

    @NotNull(message = "El category es obligatorio")
    @Schema(description = "Nombre de la categoría", example = "Bebidas")
    private String category;

    @NotNull(message = "El tag es obligatorio")
    @Schema(description = "Etiqueta corta identificadora", example = "bebidas")
    private String tag;
}
