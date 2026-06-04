package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoInvoice extends JpaRepository<Invoice, Integer> {
    List<Invoice> findByCustomerId(Integer customerId);
}
