package com.kal.invoice_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class ApiException extends RuntimeException{
        private HttpStatus status;

        public ApiException(String message, HttpStatus status){
            super(message);
            this.status = status;
        }

}
