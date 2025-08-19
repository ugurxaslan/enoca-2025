package com.example.enoca_project_2025.DTO;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @Null
    private Long id;

    private String name;

    private Double price;

    private Integer stockQuantity;

}
