package com.kal.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoCustomerFeign {
    private Integer customerId;
    private String name;
    private String email;
}
