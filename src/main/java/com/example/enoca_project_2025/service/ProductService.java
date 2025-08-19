package com.example.enoca_project_2025.service;

import org.springframework.stereotype.Service;

import com.example.enoca_project_2025.DTO.CreateProduct;
import com.example.enoca_project_2025.DTO.ProductDTO;
import com.example.enoca_project_2025.entity.Product;
import com.example.enoca_project_2025.exception.customException.ProductNotFoundException;
import com.example.enoca_project_2025.mapper.ProductMapper;
import com.example.enoca_project_2025.repository.CartItemRepository;
import com.example.enoca_project_2025.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO createProduct(CreateProduct createProduct) {
        Product product = ProductMapper.toCreateEntity(createProduct);
        Product saved = productRepository.save(product);
        return ProductMapper.toDTO(saved);
    }

    @Transactional
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());

        Product updated = productRepository.save(product);
        return ProductMapper.toDTO(updated);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        cartItemRepository.deleteByProduct(product); // önce cart_items temizle
        productRepository.delete(product);
    }
}