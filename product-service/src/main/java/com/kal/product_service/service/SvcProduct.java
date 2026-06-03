package com.kal.product_service.service;


import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SvcProduct {
    List<DtoProductOut> getProducts();
    DtoProductOut getProduct(Integer id);
    String createProduct(DtoProductIn in);
    String updateProduct(Integer id, DtoProductIn in);
    String updateStock(Integer id, Integer quantity);
    String enableProduct(Integer id);
    String disableProduct(Integer id);

}
