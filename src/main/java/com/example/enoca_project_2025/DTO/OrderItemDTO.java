package com.example.enoca_project_2025.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private Long id;

    private Integer orderQuantity;

    private Double orderPrice;

    private ProductDTO product;
}
