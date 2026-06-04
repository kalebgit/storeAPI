package com.kal.invoice_service.service;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.dto.DtoCartItemOut;
import com.kal.invoice_service.dto.DtoCustomerFeign;
import com.kal.invoice_service.dto.DtoProductOut;
import com.kal.invoice_service.exception.ApiException;
import com.kal.invoice_service.exception.DBAccessException;
import com.kal.invoice_service.feign.CustomerClient;
import com.kal.invoice_service.feign.ProductClient;
import com.kal.invoice_service.mapper.MapperCartItem;
import com.kal.invoice_service.repository.RepoCartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SvcCartItemImp implements SvcCartItem{

    private final RepoCartItem repoCartItem;
    private final MapperCartItem mapperCartItem;
    private final CustomerClient customerClient;
    private final ProductClient productClient;


    @Override
    public String add(DtoCartItemIn cartItemIn) {
        //tenemos que verificar que el usuario exista
        //pero esto ya se hace mediante auth del gateway

        //entonces lo que debemos verificar es que el customer
        //exista con el mismo id del usuario
        //y verificamos si el producto exista
        //esto lo hacemos mediante feign pues cada servicio tiene su
        // propia base de datos y no es posbile tener alguna referencia
        // hacia estas bases de datos internamente y de forma directa




        //esto lo hacemos con el objetivo de verificar que existan tanto el customer como el product
        customerClient.getCustomer(cartItemIn.getCustomerId());
        productClient.getProduct(cartItemIn.getProductId());
        try{
            repoCartItem.save(mapperCartItem.dtoCartItemInToCartItem(cartItemIn));
        }catch (DataAccessException e){
            throw new DBAccessException(e);
        }
        return "Producto agregado al carrito";
    }

    @Override
    public String remove(Integer cartItemId) {
        try{
            repoCartItem.deleteById(cartItemId);
        }catch(DataAccessException e){
            throw new DBAccessException(e);
        }
        return "El producto fue eliminado del carrito";
    }

    @Override
    public String clearCart(Integer customerId) {
        try{
            repoCartItem.deleteCartItemByCustomerId(customerId);
        }catch(DataAccessException e){
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");
            if (msg.contains("fk_cart_item_id")) throw new ApiException("El id de este producto en el carrito no existe", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
        return "El carrito completo fue eliminado";
    }

    @Override
    public List<DtoCartItemOut> getCart(Integer customerId) {
        try{

            return mapperCartItem.cartItemsToDtoCartItemsOut(repoCartItem.getCartItemsByCustomerId(customerId));

        }catch(DataAccessException e){
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");
            if (msg.contains("fk_cart_item_id")) throw new ApiException("El id de este producto en el carrito no existe", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
    }
}
