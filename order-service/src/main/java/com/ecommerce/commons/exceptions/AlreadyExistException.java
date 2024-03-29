package com.ecommerce.commons.exceptions;

import lombok.Data;

@Data
public class AlreadyExistException extends RuntimeException {
    private String message;

    public AlreadyExistException(String message) {
        super(message);
        this.message = message;
    }
}
