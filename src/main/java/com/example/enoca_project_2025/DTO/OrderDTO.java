package com.example.enoca_project_2025.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private String orderCode;

    private Double totalPrice;

    private CustomerDTO customer;

    private List<OrderItemDTO> orderItems;

}
