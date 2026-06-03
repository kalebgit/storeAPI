package com.kal.invoice_service.feign;

import com.kal.invoice_service.dto.DtoProductOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="product-service")
public interface ProductClient {

    @GetMapping("/product/{id}")
    DtoProductOut getProduct(@PathVariable Integer id);

    @PatchMapping("/product/{id}/stock")
    String updateStock(@PathVariable Integer id, @RequestBody Integer quantity);
}
