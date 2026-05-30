package com.kal.product_service.controller;

import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.service.SvcProduct;
import com.kal.product_service.service.SvcProductImp;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CtrlProduct {

    private final SvcProduct svcProduct;

    @GetMapping
    public ResponseEntity<List<DtoProductOut>> getProducts(){
        return ResponseEntity.ok(svcProduct.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoProductOut> getProduct(@PathVariable Integer id){
        return ResponseEntity.ok(svcProduct.getProduct(id));
    }


}
