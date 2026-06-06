package com.kal.invoice_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_info")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // CARD, CASH, TRANSFER
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    // Solo aplica si paymentMethod = CARD
    @Column(name = "card_last_four")
    private String cardLastFour;

    @OneToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;
}
