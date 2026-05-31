package com.kal.product_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class DtoProductOut {

    private Integer product_id;

    private String gtin;

    private String name;

    private String description;

    private Float price;

    private Integer stock;

    private Integer category_id;

}
