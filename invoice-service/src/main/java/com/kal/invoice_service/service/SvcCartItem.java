package com.kal.invoice_service.service;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.dto.DtoCartItemOut;
import com.kal.invoice_service.entity.CartItem;

import java.util.List;

public interface SvcCartItem {

    //que es lo que se podria hacer con un item del carrito
    //crearlo (cuando se agrega un item al carrito)
    //borrarlo
    // no se actualiza (pues no es una accion viable a la hora de usar carritos)
    // y pues leer los cartitems es algo basico

    String add(DtoCartItemIn cartItemIn);
    String remove(String cartItemId);
    String clearCart(String customerId);
    List<DtoCartItemOut> getCart(String customerId);
}
