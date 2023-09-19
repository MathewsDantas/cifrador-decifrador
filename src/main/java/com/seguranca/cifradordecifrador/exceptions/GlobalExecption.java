package com.seguranca.cifradordecifrador.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExecption extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidTextException.class)
    public ResponseEntity<Object> handleInvalidTextException(InvalidTextException ex) {
        // Defina a mensagem de erro e o código de status adequados
        ErrorMessage errorMessage = new ErrorMessage("Texto inválido", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(EmptyKeyException.class)
    public ResponseEntity<Object> handleEmptyKeyException(EmptyKeyException ex) {
        // Defina a mensagem de erro e o código de status adequados
        ErrorMessage errorMessage = new ErrorMessage("Chave vazia", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
