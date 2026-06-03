package com.kal.auth_service.feign;

import com.kal.auth_service.dto.DtoCustomerFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="customer-service")
public interface CustomerClient {

    @PostMapping("/customer")
    void createCustomer(@RequestBody DtoCustomerFeign customerCreateIn);
}
