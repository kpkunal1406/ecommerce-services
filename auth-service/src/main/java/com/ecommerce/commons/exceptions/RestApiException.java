package com.ecommerce.commons.exceptions;

import lombok.Data;

@Data
public class RestApiException extends RuntimeException {
    private String message;

    public RestApiException(String message) {
        super(message);
        this.message = message;
    }
}
