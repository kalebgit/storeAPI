package com.kal.product_service.service;


import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SvcProduct {
    public List<DtoProductOut> getProducts();
    public DtoProductOut getProduct(Integer id);
    public String createProduct(DtoProductIn in);
    public String updateProduct(Integer id, DtoProductIn in);
    public String enableProduct(Integer id);
    public String disableProduct(Integer id);

}
