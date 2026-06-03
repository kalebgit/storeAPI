package com.kal.product_service.service;

import com.kal.product_service.dto.DtoProductIn;
import com.kal.product_service.dto.DtoProductOut;
import com.kal.product_service.entity.Product;
import com.kal.product_service.exception.ApiException;
import com.kal.product_service.exception.DBAccessException;
import com.kal.product_service.mapper.MapperProduct;
import com.kal.product_service.repository.RepoProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return mapperProduct.productsToDtoProductsOut( repoProduct.findAll());
    }

    @Override
    public DtoProductOut getProduct(Integer id) {
        return mapperProduct.productToDtoProductOut(repoProduct.findById(id).orElseThrow(()->new ApiException("Producto no encontrado con id: " + id, HttpStatus.NOT_FOUND)));
    }

    @Override
    public String createProduct(DtoProductIn in) {
        //TODO: tenemos que poner condiciones antes de crearlo? como revisar que no exista el nombre, o algo unico
        try{

            repoProduct.save(mapperProduct.dtoproductInToProduct(in));

        }catch(DataAccessException e){
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");

            if(msg.contains("ux_product_gtin")) throw new ApiException("El gtin de este producto ya esta registrado", HttpStatus.NOT_FOUND);
            if(msg.contains("ux_product_name")) throw new ApiException("El nombre de este producto ya esta registrado", HttpStatus.CONFLICT);
            if(msg.contains("fk_product_category")) throw new ApiException("El id de categoria no existe", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
        return "El producto ha sido registrado";
    }

    @Override
    public String updateProduct(Integer id, DtoProductIn in) {
        Product product = repoProduct.findById(id)
                .orElseThrow(() -> new ApiException("Producto no encontrado con id: " + id, HttpStatus.NOT_FOUND));
        product.setGtin(in.getGtin());
        product.setName(in.getName());
        product.setDescription(in.getDescription());
        product.setPrice(in.getPrice());
        product.setStock(in.getStock());
        product.setCategory_id(in.getCategory_id());
        try {
            repoProduct.save(product);
        } catch (DataAccessException e) {
            Throwable cause = e.getMostSpecificCause();
            String msg = Optional.ofNullable(cause.getMessage()).orElse("");
            if (msg.contains("ux_product_gtin")) throw new ApiException("El gtin de este producto ya esta registrado", HttpStatus.CONFLICT);
            if (msg.contains("ux_product_name")) throw new ApiException("El nombre de este producto ya esta registrado", HttpStatus.CONFLICT);
            if (msg.contains("fk_product_category")) throw new ApiException("El id de categoria no existe", HttpStatus.CONFLICT);
            throw new DBAccessException(e);
        }
        return "El producto ha sido actualizado";
    }

    @Override
    public String enableProduct(Integer id) {
        Product product = repoProduct.findById(id)
                .orElseThrow(() -> new ApiException("Producto no encontrado con id: " + id, HttpStatus.NOT_FOUND));
        product.setStatus(1);
        repoProduct.save(product);
        return "El producto ha sido activado";
    }

    @Override
    public String disableProduct(Integer id) {
        Product product = repoProduct.findById(id)
                .orElseThrow(() -> new ApiException("Producto no encontrado con id: " + id, HttpStatus.NOT_FOUND));
        product.setStatus(0);
        repoProduct.save(product);
        return "El producto ha sido desactivado";
    }
}
