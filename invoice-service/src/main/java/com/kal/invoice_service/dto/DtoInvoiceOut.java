package com.kal.invoice_service.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DtoInvoiceOut {
    private Integer id;
    private Integer customerId;
    private Float subtotal;
    private Float taxes;
    private Float total;
    private LocalDateTime createdAt;

    List<DtoInvoiceItemOut> items;
}
