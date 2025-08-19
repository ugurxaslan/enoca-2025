package com.example.enoca_project_2025.mapper;

import com.example.enoca_project_2025.DTO.CreateCustomer;
import com.example.enoca_project_2025.DTO.CustomerDTO;
import com.example.enoca_project_2025.entity.Customer;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setCartId(customer.getCart() != null ? customer.getCart().getId() : null);
        return dto;
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        return customer;
    }

    public static Customer toCreateEntity(CreateCustomer createCustomer) {
        if (createCustomer == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setName(createCustomer.getName());
        customer.setEmail(createCustomer.getEmail());
        return customer;
    }

}
