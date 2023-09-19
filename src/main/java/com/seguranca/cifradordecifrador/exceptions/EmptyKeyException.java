package com.seguranca.cifradordecifrador.exceptions;

public class EmptyKeyException extends RuntimeException {
    public EmptyKeyException(String message) {
        super(message);
    }
}
