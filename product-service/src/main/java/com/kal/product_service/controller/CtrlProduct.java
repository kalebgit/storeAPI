package com.kal.product_service.controller;

import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.service.SvcProduct;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión del catálogo de productos")
public class CtrlProduct {

    private final SvcProduct svcProduct;

    @Operation(summary = "Listar todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos")
    @GetMapping
    public ResponseEntity<List<DtoProductOut>> getProducts() {
        return ResponseEntity.ok(svcProduct.getProducts());
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DtoProductOut> getProduct(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.getProduct(id));
    }

    @Operation(summary = "Registrar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody DtoProductIn in) {
        return ResponseEntity.status(HttpStatus.CREATED).body(svcProduct.createProduct(in));
    }

    @Operation(summary = "Actualizar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id,
            @Valid @RequestBody DtoProductIn in) {
        return ResponseEntity.ok(svcProduct.updateProduct(id, in));
    }

    @Operation(summary = "Actualizar stock de un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Stock actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PutMapping("/{id}/stock")
    public ResponseEntity<String> updateStock(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id,
            @RequestBody Integer quantity) {
        return ResponseEntity.ok(svcProduct.updateStock(id, quantity));
    }

    @Operation(summary = "Activar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto activado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enableProduct(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.enableProduct(id));
    }

    @Operation(summary = "Desactivar un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto desactivado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disableProduct(
            @Parameter(description = "ID del producto", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(svcProduct.disableProduct(id));
    }
}
