package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoCartItem extends JpaRepository<CartItem, Integer> {
}
