package com.example.enoca_project_2025.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enoca_project_2025.DTO.CreateCustomer;
import com.example.enoca_project_2025.DTO.CustomerDTO;
import com.example.enoca_project_2025.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@Valid @RequestBody CreateCustomer createCustomer) {
        CustomerDTO responseDTO = customerService.addCustomer(createCustomer);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
