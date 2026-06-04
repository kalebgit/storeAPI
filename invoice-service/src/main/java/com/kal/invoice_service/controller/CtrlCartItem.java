package com.kal.invoice_service.controller;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.dto.DtoCartItemOut;
import com.kal.invoice_service.service.SvcCartItem;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-item")
@RequiredArgsConstructor
public class CtrlCartItem {

    private final SvcCartItem svcCartItem;

    @GetMapping
    ResponseEntity<List<DtoCartItemOut>> getCart(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(svcCartItem.getCart(Integer.parseInt(userId)));
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestHeader("X-User-Id") String userId, @RequestBody DtoCartItemIn in){
        in.setCustomerId((Integer.parseInt(userId)));
        return ResponseEntity.status(HttpStatus.CREATED).body(svcCartItem.add(in));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable Integer id){
        return ResponseEntity.ok(svcCartItem.remove(id));
    }

    @DeleteMapping
    public ResponseEntity<String> clearCart(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.ok(svcCartItem.clearCart(Integer.parseInt(userId)));
    }




}
