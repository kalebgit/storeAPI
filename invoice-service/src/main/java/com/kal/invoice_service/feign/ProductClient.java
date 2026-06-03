package com.kal.invoice_service.feign;

import com.kal.invoice_service.dto.DtoProductOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="product-service")
public interface ProductClient {

    @GetMapping("/product/{id}")
    public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id);
}
