package com.kal.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
public class DtoProductIn {
    @Pattern(regexp = "^\\+?\\d{13}$", message = "El gtin tiene un formato inválido")
    @NotNull(message="el gtin es obligatorio")
    private String gtin;

    @NotNull(message="El product es obligatorio")
    private String name;

    @NotNull(message="El description es obligatorio")
    private String description;

    @Min(value = 0)
    @NotNull(message="El price es obligatorio")
    private Float price;

    @NotNull(message="El stock es obligatorio")
    private Integer stock;

    @NotNull(message="El category_id es obligatorio")
    private Integer category_id;
}
