package com.kal.invoice_service.dto;

import lombok.Data;

@Data
public class DtoInvoiceItemOut {
    private Integer id;
    private Integer productId;
    private String productName;
    private Float unitPrice;
    private Integer quantity;
    private Float subtotal;
}
