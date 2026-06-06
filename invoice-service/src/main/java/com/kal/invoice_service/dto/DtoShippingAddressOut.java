package com.kal.invoice_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Dirección de envío de la factura")
public class DtoShippingAddressOut {

    @Schema(description = "Calle y número", example = "Av. Universidad 3000")
    private String street;

    @Schema(description = "Ciudad", example = "Ciudad de México")
    private String city;

    @Schema(description = "Estado", example = "CDMX")
    private String state;

    @Schema(description = "Código postal", example = "04510")
    private String zipCode;
}
