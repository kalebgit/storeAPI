package com.kal.invoice_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DtoProductOut {

    private Integer productId;

    private String gtin;

    private String name;

    private String description;

    private Float price;

    private Integer stock;

    private Integer categoryId;

}
