package com.kal.invoice_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoCartItemIn {

    private Integer customerId;

    private Integer productId;

    private Integer quantity;

}
