package com.kal.invoice_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerFeign {
    private Integer customerId;
    private String name;
    private String email;
}
