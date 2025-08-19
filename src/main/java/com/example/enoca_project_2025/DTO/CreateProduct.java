package com.example.enoca_project_2025.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProduct {
    private String name;
    private Double price;
    private Integer stockQuantity;

}
