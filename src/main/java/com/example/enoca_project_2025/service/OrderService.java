package com.example.enoca_project_2025.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.enoca_project_2025.DTO.OrderDTO;
import com.example.enoca_project_2025.entity.Cart;
import com.example.enoca_project_2025.entity.CartItem;
import com.example.enoca_project_2025.entity.Customer;
import com.example.enoca_project_2025.entity.Order;
import com.example.enoca_project_2025.entity.OrderItem;
import com.example.enoca_project_2025.entity.Product;
import com.example.enoca_project_2025.exception.customException.CartNotFoundException;
import com.example.enoca_project_2025.exception.customException.CustomerNotFoundException;
import com.example.enoca_project_2025.exception.customException.EmptyCartException;
import com.example.enoca_project_2025.exception.customException.OrderCodeNotFoundException;
import com.example.enoca_project_2025.exception.customException.OutOfStockException;
import com.example.enoca_project_2025.mapper.OrderMapper;
import com.example.enoca_project_2025.repository.CartRepository;
import com.example.enoca_project_2025.repository.CustomerRepository;
import com.example.enoca_project_2025.repository.OrderRepository;
import com.example.enoca_project_2025.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderDTO placeOrder(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        Cart cart = cartRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new CartNotFoundException(customerId));

        if (cart.getCartItems().isEmpty()) {
            throw new EmptyCartException(customerId);
        }

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderItems(new ArrayList<OrderItem>());

        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            if (cartItem.getQuantity() > product.getStockQuantity()) {
                throw new OutOfStockException(customerId);
            }

            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderQuantity(cartItem.getQuantity());
            orderItem.setOrderPrice(product.getPrice());
            orderItem.setProduct(product);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        order.calculateTotalPrice();
        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();
        cartRepository.save(cart);

        return OrderMapper.toDTO(savedOrder);
    }

    @Transactional
    public OrderDTO getOrderByCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode)
                .orElseThrow(() -> new OrderCodeNotFoundException(orderCode));

        return OrderMapper.toDTO(order);
    }

    @Transactional
    public List<OrderDTO> getOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

}
