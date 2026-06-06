package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoShippingAddress extends JpaRepository<ShippingAddress, Integer> {
}
