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
public class CartDTO {

    private Long id;

    private Double totalPrice;

    private CustomerDTO customer;

    private List<CartItemDTO> cartItems;

}
