package com.kal.product_service.exception;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;

@AllArgsConstructor
public class DBAccessException extends RuntimeException{
    private DataAccessException exception;
}
