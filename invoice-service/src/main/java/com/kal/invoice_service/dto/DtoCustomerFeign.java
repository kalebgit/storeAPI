package com.kal.invoice_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCustomerFeign {
    private Integer id;
    private String name;
    private String email;
}
