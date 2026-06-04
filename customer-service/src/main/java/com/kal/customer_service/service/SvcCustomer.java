package com.kal.customer_service.service;

import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;

public interface SvcCustomer {
    void create(DtoCustomerFeign req);
    Customer findById(Integer id);
}
