package com.example.enoca_project_2025.exception.customException;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("This email is already exist: " + email);
    }

}
