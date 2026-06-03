package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepoCartItem extends JpaRepository<CartItem, Integer> {

    void deleteCartItemByCustomerId(Integer customerId);

    List<CartItem> getCartItemsByCustomerId(Integer customerId);

}
