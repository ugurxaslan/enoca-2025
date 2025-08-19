package com.example.enoca_project_2025.exception.customException;

public class OrderCodeNotFoundException extends RuntimeException {
    public OrderCodeNotFoundException(String orderCode) {
        super("Order with code " + orderCode + " not found.");
    }

}
