package com.example.enoca_project_2025.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.enoca_project_2025.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional query methods can be defined here if needed

}
