package com.example.enoca_project_2025.mapper;

import com.example.enoca_project_2025.DTO.CreateProduct;
import com.example.enoca_project_2025.DTO.ProductDTO;
import com.example.enoca_project_2025.entity.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        return dto;
    }

    public static Product toEntity(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        return product;
    }

    public static Product toCreateEntity(CreateProduct createProduct) {
        if (createProduct == null) {
            return null;
        }
        Product product = new Product();
        product.setName(createProduct.getName());
        product.setPrice(createProduct.getPrice());
        product.setStockQuantity(createProduct.getStockQuantity());
        return product;
    }
}
