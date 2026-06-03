package com.kal.invoice_service.dto;

import lombok.Data;

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
