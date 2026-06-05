package com.kal.customer_service.controller;

import com.kal.customer_service.dto.DtoCustomerFeign;
import com.kal.customer_service.entity.Customer;
import com.kal.customer_service.mapper.MapperCustomer;
import com.kal.customer_service.service.SvcCustomer;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Gestión de clientes de la tienda")
public class CtrlCustomer {

    private final SvcCustomer svcCustomer;
    private final MapperCustomer mapperCustomer;

    @Hidden
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DtoCustomerFeign req) {
        svcCustomer.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Obtener cliente por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DtoCustomerFeign> getCustomer(
            @Parameter(description = "ID del cliente", example = "1")
            @PathVariable Integer id) {
        return ResponseEntity.ok(mapperCustomer.customerToDtoCustomerFeign(svcCustomer.findById(id)));
    }

    @Operation(summary = "Obtener perfil del cliente autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Perfil del cliente"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @GetMapping("/me")
    public ResponseEntity<Customer> getMe(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(svcCustomer.findById(Integer.parseInt(userId)));
    }
}
