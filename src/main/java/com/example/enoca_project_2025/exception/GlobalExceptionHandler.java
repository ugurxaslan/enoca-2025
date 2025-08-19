package com.example.enoca_project_2025.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.enoca_project_2025.exception.customException.CartNotFoundException;
import com.example.enoca_project_2025.exception.customException.CustomerNotFoundException;
import com.example.enoca_project_2025.exception.customException.EmailAlreadyExistsException;
import com.example.enoca_project_2025.exception.customException.EmptyCartException;
import com.example.enoca_project_2025.exception.customException.OrderCodeNotFoundException;
import com.example.enoca_project_2025.exception.customException.OutOfStockException;
import com.example.enoca_project_2025.exception.customException.ProductNotFoundException;

import io.swagger.v3.oas.annotations.Hidden;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Hidden
@ControllerAdvice(basePackages = "com.example.enoca_project_2025.controller")
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCartNotFound(CartNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleCustomerNotFound(CustomerNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmptyCartException.class)
    public ResponseEntity<Map<String, Object>> handleEmptyCart(EmptyCartException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OrderCodeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleOrderCodeNotFound(OrderCodeNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, Object>> handleEmailExists(EmailAlreadyExistsException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<Map<String, Object>> handleOutOfStock(OutOfStockException ex) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }
}
