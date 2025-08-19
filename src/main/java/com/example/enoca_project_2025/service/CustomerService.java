package com.example.enoca_project_2025.service;

import org.springframework.stereotype.Service;

import com.example.enoca_project_2025.DTO.CreateCustomer;
import com.example.enoca_project_2025.DTO.CustomerDTO;
import com.example.enoca_project_2025.entity.Cart;
import com.example.enoca_project_2025.entity.Customer;
import com.example.enoca_project_2025.exception.customException.EmailAlreadyExistsException;
import com.example.enoca_project_2025.mapper.CustomerMapper;
import com.example.enoca_project_2025.repository.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerDTO addCustomer(CreateCustomer createCustomer) {

        if (customerRepository.existsByEmail(createCustomer.getEmail())) {
            throw new EmailAlreadyExistsException(createCustomer.getEmail());
        }

        Customer customer = CustomerMapper.toCreateEntity(createCustomer);
        Cart cart = new Cart();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toDTO(savedCustomer);
    }

}
