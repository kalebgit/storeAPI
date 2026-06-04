package com.kal.customer_service.dto;

// DTO que recibe auth-service al crear un cliente tras el registro
public record DtoCustomerFeign(
        Integer customerId,
        String email,
        String name
) {}
