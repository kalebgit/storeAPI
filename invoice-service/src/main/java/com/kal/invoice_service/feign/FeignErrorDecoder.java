package com.kal.invoice_service.feign;

import com.kal.invoice_service.exception.ApiException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    //en el caso de que algun cliente devuevla un estado de error por no haber encontrado el producto o el usuario
    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 404 -> new ApiException("Recurso no encontrado", HttpStatus.NOT_FOUND);
            case 409 -> new ApiException("Conflicto con recurso existente", HttpStatus.CONFLICT);
            case 503 -> new ApiException("Servicio no disponible", HttpStatus.SERVICE_UNAVAILABLE);
            default  -> new ApiException("Error en servicio externo", HttpStatus.INTERNAL_SERVER_ERROR);
        };
    }
}