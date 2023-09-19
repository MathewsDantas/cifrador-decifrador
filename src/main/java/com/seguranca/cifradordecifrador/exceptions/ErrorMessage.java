package com.seguranca.cifradordecifrador.exceptions;

public class ErrorMessage {
    private final int status;
    private final String error;
    private final String message;

    public ErrorMessage( int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
