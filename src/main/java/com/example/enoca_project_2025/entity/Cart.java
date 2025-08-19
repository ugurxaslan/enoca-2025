package com.example.enoca_project_2025.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart extends BaseEntity {

    @Column(name = "total_price", nullable = false)
    private Double totalPrice = 0.0;

    @OneToOne(mappedBy = "cart", optional = false)
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        if (cartItems != null && !cartItems.isEmpty()) {
            this.totalPrice = cartItems.stream()
                    .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum();
        } else {
            this.totalPrice = 0.0;
        }
    }

}
