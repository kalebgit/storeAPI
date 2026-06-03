package com.kal.customer_service.controller;

import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import com.kal.customer_service.service.SvcCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CtrlCustomer {

    private final SvcCustomer svcCustomer;

    // endpoint interno — llamado por auth-service via Feign al registrar un usuario
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DtoCustomerFeign req) {
        svcCustomer.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // el Gateway extrae el user_id del JWT y lo propaga como header X-User-Id
    @GetMapping("/me")
    public ResponseEntity<Customer> getMe(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(svcCustomer.findById(Integer.parseInt(userId)));
    }
}
