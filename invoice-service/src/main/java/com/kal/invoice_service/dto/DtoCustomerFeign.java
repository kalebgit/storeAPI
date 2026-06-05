package com.kal.invoice_service.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerFeign {
    @JsonAlias("customerId")
    private Integer id;
    private String name;
    private String email;
}
