package com.kal.invoice_service.mapper;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.dto.DtoCartItemOut;
import com.kal.invoice_service.entity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperCartItem {

    CartItem dtoCartItemInToCartItem(DtoCartItemIn cartItemIn);

    List<DtoCartItemOut> cartItemsToDtoCartItemsOut(List<CartItem> cartItems);



}
