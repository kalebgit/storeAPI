package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Datos para crear un cupón de descuento")
public class DtoCouponIn {

    @Schema(description = "Código único del cupón", example = "DESCUENTO10")
    private String code;

    @Schema(description = "Porcentaje de descuento (0.10 = 10%)", example = "0.10")
    private Float discount;
}
