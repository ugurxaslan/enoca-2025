package com.example.enoca_project_2025.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enoca_project_2025.DTO.OrderDTO;
import com.example.enoca_project_2025.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder/{customerId}")
    public ResponseEntity<OrderDTO> placeOrder(@PathVariable Long customerId) {
        OrderDTO orderDTO = orderService.placeOrder(customerId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderDTO> getOrderForCode(@PathVariable String orderCode) {
        OrderDTO orderDTO = orderService.getOrderByCode(orderCode);
        return ResponseEntity.ok(orderDTO);
    }

    @GetMapping("/getAllOrder/{customerId}")
    public ResponseEntity<List<OrderDTO>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        List<OrderDTO> orders = orderService.getOrdersForCustomer(customerId);
        return ResponseEntity.ok(orders);
    }
}
