package com.luisanthony.portfolio.inventory_management_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        String errorMessage = "Error data integrity violation";

        // DataIntegrityViolationException porque puede ser por UNIQUE, NOT NULL, o Foreign Key.
        // El contenido de ex.getCause().getMessage() varía entre bases de datos.
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            String causeMessage = ex.getCause().getMessage().toLowerCase();
            if (causeMessage.contains("duplicate entry") || causeMessage.contains("unique constraint")) {
                errorMessage = "The name provided already exists. Please choose a different name";
            } else if (causeMessage.contains("foreign key constraint")
                    || causeMessage.contains("violates foreign key")) {
                errorMessage = "This resource cannot be removed because it is associated with other elements.";
            }
        }
        errors.put("error", errorMessage);
        return new ResponseEntity<>(errors, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> hanldeResourceNotFoundException(ResourceNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("error", ex.getMessage());

        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("Error", "An unexpected server error has occurred");
        ex.printStackTrace(); // Para ver el stack trace en la consola del servidor

        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
