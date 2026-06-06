package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoPaymentInfo extends JpaRepository<PaymentInfo, Integer> {
}
