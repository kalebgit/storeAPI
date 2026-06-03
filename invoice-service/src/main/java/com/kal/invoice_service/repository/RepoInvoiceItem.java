package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoInvoiceItem extends JpaRepository<InvoiceItem, Integer> {
    List<InvoiceItem> findByInvoiceId(Integer invoiceId);
}
