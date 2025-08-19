package com.example.enoca_project_2025.exception.customException;

public class EmptyCartException extends RuntimeException {
    public EmptyCartException(Long customerId) {
        super("Cart is empty for customer with ID: " + customerId);
    }

}
