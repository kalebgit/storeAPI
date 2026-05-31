package com.kal.category_service.controller;

import com.kal.category_service.dto.DtoCategoryIn;
import com.kal.category_service.entity.Category;
import com.kal.category_service.service.SvcCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CtrlCategory {

    private final SvcCategory svcCategory;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(svcCategory.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Category>> findActive() {
        return ResponseEntity.ok(svcCategory.findActive());
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody DtoCategoryIn in) {
        svcCategory.create(in);
        return ResponseEntity.status(HttpStatus.CREATED).body("La categoría ha sido registrada");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @Valid @RequestBody DtoCategoryIn in) {
        svcCategory.update(in, id);
        return ResponseEntity.ok("La categoría ha sido actualizada");
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<String> enable(@PathVariable Integer id) {
        svcCategory.enable(id);
        return ResponseEntity.ok("La categoría ha sido activada");
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<String> disable(@PathVariable Integer id) {
        svcCategory.disable(id);
        return ResponseEntity.ok("La categoría ha sido desactivada");
    }
}
