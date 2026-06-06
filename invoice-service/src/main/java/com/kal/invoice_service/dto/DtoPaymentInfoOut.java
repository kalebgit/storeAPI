package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información de pago de la factura")
public class DtoPaymentInfoOut {

    @Schema(description = "Método de pago", example = "CARD")
    private String paymentMethod;

    @Schema(description = "Últimos 4 dígitos de la tarjeta", example = "1234")
    private String cardLastFour;
}
