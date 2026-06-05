package com.kal.invoice_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoCartItemOut {

    private Integer id;

    private Integer customerId;

    private Integer productId;

    private String name;

    private Float price;

    private Integer quantity;


}
