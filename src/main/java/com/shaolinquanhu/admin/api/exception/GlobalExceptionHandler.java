package com.shaolinquanhu.admin.api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author dahl
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException enfe) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la entidad: " + enfe.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException iae) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argumento invalido: " + iae.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ha ocurrido un error: " + e.getMessage());
    }
}
