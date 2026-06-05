package com.kal.category_service.controller;

import com.kal.category_service.dto.DtoCategoryIn;
import com.kal.category_service.entity.Category;
import com.kal.category_service.service.SvcCategory;
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
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CtrlCategory {

    private final SvcCategory svcCategory;

    @Operation(summary = "Listar todas las categorías")
    @ApiResponse(responseCode = "200", description = "Lista de categorías")
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(svcCategory.findAll());
    }

    @Operation(summary = "Listar categorías activas")
    @ApiResponse(responseCode = "200", description = "Lista de categorías activas")
    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive() {
        return ResponseEntity.ok(svcCategory.findActive());
    }

    @Operation(summary = "Crear categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Categoría creada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DtoCategoryIn in) {
        svcCategory.create(in);
        return ResponseEntity.status(HttpStatus.CREATED).body("La categoría ha sido registrada");
    }

    @Operation(summary = "Actualizar categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría actualizada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable Integer id,
            @Valid @RequestBody DtoCategoryIn in) {
        svcCategory.update(in, id);
        return ResponseEntity.ok("La categoría ha sido actualizada");
    }

    @Operation(summary = "Activar categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría activada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enable(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable Integer id) {
        svcCategory.enable(id);
        return ResponseEntity.ok("La categoría ha sido activada");
    }

    @Operation(summary = "Desactivar categoría")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoría desactivada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada"),
            @ApiResponse(responseCode = "401", description = "Token inválido o ausente")
    })
    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disable(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable Integer id) {
        svcCategory.disable(id);
        return ResponseEntity.ok("La categoría ha sido desactivada");
    }
}
