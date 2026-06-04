package com.kal.invoice_service.controller;

import com.kal.invoice_service.dto.DtoInvoiceOut;import com.kal.invoice_service.service.SvcInvoice;import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class CtrlInvoice {

    private final SvcInvoice svcInvoice;

    @PostMapping
    public ResponseEntity<DtoInvoiceOut> create(@RequestHeader("X-User-Id") String userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(svcInvoice.create(Integer.parseInt(userId)));
    }
}
