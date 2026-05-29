package com.kal.product_service.service;

import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.mapper.MapperProduct;
import com.kal.product_service.repository.RepoProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//NOTA IMPORTANTE: a partir de versiones 4 o mas de spring
//ya no es necesraio usar el autowired con que tenga un unico
//constructor esta inyeccion de dependencias se hace automaticamente/
//por lo que usare solo el constructor de lombok
@RequiredArgsConstructor
public class SvcProductImp implements SvcProduct{
    private final RepoProduct repoProduct;
    private final MapperProduct mapperProduct;

    
    @Override
    public List<DtoProductOut> getProducts() {
        return List.of();
    }

    @Override
    public DtoProductOut getProduct(Integer id) {
        return null;
    }

    @Override
    public String createProduct(DtoProductIn in) {
        return "";
    }

    @Override
    public String updateProduct(Integer id, DtoProductIn in) {
        return "";
    }

    @Override
    public String enableProduct(Integer id) {
        return "";
    }

    @Override
    public String disableProduct(Integer id) {
        return "";
    }
}
