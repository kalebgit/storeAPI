package com.kal.category_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DtoCategoryIn {
    @NotNull(message = "El category es obligatorio")
    private String category;

    @NotNull(message = "El tag es obligatorio")
    private String tag;
}
