package com.kal.product_service.mapper;


import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapperProduct {
    //los dtos in
    DtoProductIn productToDtoProductIn(Product product);
    Product dtoproductInToProduct(DtoProductIn DtoProductIn);

    //los dtos out
    DtoProductOut productToDtoProductOut(Product product);
    Product dtoProductOutToProduct(DtoProductOut product);

    List<DtoProductOut> productsToDtoProductsOut(List<Product> products);
}
