package com.kal.invoice_service.controller;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.dto.DtoCartItemOut;
import com.kal.invoice_service.service.SvcCartItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
@Tag(name = "Carrito de compras", description = "Gestión del carrito de compras del cliente autenticado")
public class CtrlCartItem {

    private final SvcCartItem svcCartItem;

    @Operation(summary = "Ver carrito del cliente autenticado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contenido del carrito"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @GetMapping
    public ResponseEntity<List<DtoCartItemOut>> getCart(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(svcCartItem.getCart(Integer.parseInt(userId)));
    }

    @Operation(summary = "Agregar producto al carrito")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto agregado al carrito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "Stock insuficiente"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping
    public ResponseEntity<String> add(@RequestHeader("X-User-Id") String userId, @RequestBody DtoCartItemIn in) {
        in.setCustomerId(Integer.parseInt(userId));
        return ResponseEntity.status(HttpStatus.CREATED).body(svcCartItem.add(in));
    }

    @Operation(summary = "Eliminar un artículo del carrito")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Artículo eliminado"),
            @ApiResponse(responseCode = "404", description = "Artículo no encontrado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(
            @Parameter(description = "ID del artículo en el carrito", example = "1") @PathVariable Integer id) {
        return ResponseEntity.ok(svcCartItem.remove(id));
    }

    @Operation(summary = "Vaciar el carrito completo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrito vaciado"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @DeleteMapping
    public ResponseEntity<String> clearCart(@RequestHeader("X-User-Id") String userId) {
        return ResponseEntity.ok(svcCartItem.clearCart(Integer.parseInt(userId)));
    }
}
