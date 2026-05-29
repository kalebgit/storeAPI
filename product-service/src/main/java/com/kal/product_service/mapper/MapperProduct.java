package com.kal.product_service.mapper;


import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperProduct {
    DtoProductIn productToDtoProductIn(Product product);
    Product productDotInToProduct(DtoProductIn DtoProductIn);
}
