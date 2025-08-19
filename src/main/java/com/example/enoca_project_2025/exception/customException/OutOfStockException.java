package com.example.enoca_project_2025.exception.customException;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(Long productId) {
        super("Product with id " + productId + " is out of stock.");
    }
}
