package com.kal.invoice_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_id", nullable = false)
    private Integer customerId;

    @Column(nullable = false)
    private Float subtotal;

    @Column(nullable = false)
    private Float taxes;

    @Column(nullable = false)
    private Float total;

    @CreationTimestamp//setea el tiempo en que fue creado automaticamente
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> items;
}

