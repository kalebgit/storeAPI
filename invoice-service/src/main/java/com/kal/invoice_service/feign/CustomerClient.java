package com.kal.invoice_service.feign;

import com.kal.invoice_service.dto.DtoCustomerFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    DtoCustomerFeign getCustomer(@PathVariable Integer id);
}
