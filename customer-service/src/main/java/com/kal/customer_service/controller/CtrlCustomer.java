package com.kal.customer_service.controller;

import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import com.kal.customer_service.mapper.MapperCustomer;
import com.kal.customer_service.service.SvcCustomer;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CtrlCustomer {

    private final SvcCustomer svcCustomer;
    private final MapperCustomer mapperCustomer;

    // endpoint interno — llamado por auth-service via Feign al registrar un usuario
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DtoCustomerFeign req) {
        svcCustomer.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //TODO: falta poner una regla de autenticacion para que no cualquiera
    // pueda obtener informacion sobre el cliente
    @GetMapping("/{id}")
    //tal vez despues cambie dtoCustomerFeign por un DtoCustomerOut
    public ResponseEntity<DtoCustomerFeign> getCustomer(@PathVariable Integer id){
        return ResponseEntity.ok(mapperCustomer.customerToDtoCustomerFeign( svcCustomer.findById(id)));
    }

    // el Gateway extrae el user_id del JWT y lo propaga como header X-User-Id
    @GetMapping("/me")
    public ResponseEntity<Customer> getMe(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(svcCustomer.findById(Integer.parseInt(userId)));
    }



}
