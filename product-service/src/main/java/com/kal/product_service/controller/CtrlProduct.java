package com.kal.product_service.controller;

import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.service.SvcProduct;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class CtrlProduct {

    private final SvcProduct svcProduct;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public ResponseEntity<List<DtoProductOut>> getProducts() {
        return ResponseEntity.ok(svcProduct.getProducts());
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.getProduct(id));
    }

    @Operation(summary = "Registrar un producto")
    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody DtoProductIn in) {
        return ResponseEntity.status(HttpStatus.CREATED).body(svcProduct.createProduct(in));
    }

    @Operation(summary = "Actualizar un producto")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Integer id, @Valid @RequestBody DtoProductIn in) {
        return ResponseEntity.ok(svcProduct.updateProduct(id, in));
    }

    @Operation(summary = "Activar un producto")
    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enableProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.enableProduct(id));
    }

    @Operation(summary = "Desactivar un producto")
    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disableProduct(@PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.disableProduct(id));
    }
}
