package com.kal.invoice_service.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coupon", uniqueConstraints = {
        @UniqueConstraint(name = "ux_coupon_code", columnNames = "code")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Cupón de descuento")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID del cupón", example = "1")
    private Integer id;

    @Column(nullable = false)
    @Schema(description = "Código único del cupón", example = "DESCUENTO10")
    private String code;

    @Column(nullable = false)
    @Schema(description = "Porcentaje de descuento (0.10 = 10%)", example = "0.10")
    private Float discount;

    @Column(nullable = false)
    @Schema(description = "Si el cupón está activo", example = "true")
    private boolean active;
}
