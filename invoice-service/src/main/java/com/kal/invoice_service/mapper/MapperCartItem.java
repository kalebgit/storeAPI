package com.kal.invoice_service.mapper;

import com.kal.invoice_service.dto.DtoCartItemIn;
import com.kal.invoice_service.entity.CartItem;
import org.mapstruct.Mapper;

@Mapper
public interface MapperCartItem {

    CartItem dtoCartItemInToCartItem(DtoCartItemIn cartItemIn);

}
