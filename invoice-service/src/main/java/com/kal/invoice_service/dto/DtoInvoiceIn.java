package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos opcionales para finalizar la compra")
public class DtoInvoiceIn {

    // Dirección de envío
    @Schema(description = "Calle y número", example = "Av. Universidad 3000")
    private String street;

    @Schema(description = "Ciudad", example = "Ciudad de México")
    private String city;

    @Schema(description = "Estado", example = "CDMX")
    private String state;

    @Schema(description = "Código postal", example = "04510")
    private String zipCode;

    // Información de pago
    @Schema(description = "Método de pago: CARD, CASH, TRANSFER", example = "CARD")
    private String paymentMethod;

    @Schema(description = "Últimos 4 dígitos de la tarjeta (solo si paymentMethod = CARD)", example = "1234")
    private String cardLastFour;

    // Cupón de descuento
    @Schema(description = "Código de cupón de descuento", example = "DESCUENTO10")
    private String couponCode;
}
