package com.example.enoca_project_2025.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")

public class Order extends BaseEntity {

    @Column(name = "order_code", nullable = false, unique = true)
    private String orderCode;

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty
    private List<OrderItem> orderItems;

    @PreUpdate
    public void calculateTotalPrice() {
        if (orderItems != null && !orderItems.isEmpty()) {
            this.totalPrice = orderItems.stream()
                    .mapToDouble(item -> item.getOrderPrice() * item.getOrderQuantity())
                    .sum();
        } else {
            this.totalPrice = 0.0;
        }
    }

    @PrePersist
    public void generateOrderCode() {
        if (this.orderCode == null) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
            String timestamp = now.format(formatter);
            this.orderCode = "ORD-" + timestamp;
        }
    }

}
