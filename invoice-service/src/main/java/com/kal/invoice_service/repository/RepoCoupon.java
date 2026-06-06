package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepoCoupon extends JpaRepository<Coupon, Integer> {
    Optional<Coupon> findByCodeAndActiveTrue(String code);
}
