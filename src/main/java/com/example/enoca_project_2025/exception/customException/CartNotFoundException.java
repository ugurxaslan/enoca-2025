package com.example.enoca_project_2025.exception.customException;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long id) {
        super("Cart not found with id: " + id);
    }

}
