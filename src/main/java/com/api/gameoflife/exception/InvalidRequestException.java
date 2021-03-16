package com.api.gameoflife.exception;

import java.io.Serializable;

public class InvalidRequestException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1003845874218575820L;

    public InvalidRequestException(String message) {
        super(message);
    }

}
