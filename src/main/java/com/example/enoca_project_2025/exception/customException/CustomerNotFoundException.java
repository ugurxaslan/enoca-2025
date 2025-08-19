package com.example.enoca_project_2025.exception.customException;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Customer with id " + customerId + " not found.");
    }

}
