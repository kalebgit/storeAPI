package com.kal.invoice_service.repository;

import com.kal.invoice_service.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepoCartItem extends JpaRepository<CartItem, Integer> {

    void deleteCartItemByCustomerId(Integer customerId);


    List<CartItem> getCartItemsByCustomerId(Integer customerId);

    //para verificar si existe un item en el carrito
    Optional<CartItem> getCartItemByCustomerIdAndProductId(Integer customerId, Integer productId);

}
