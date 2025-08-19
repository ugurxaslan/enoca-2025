package com.example.enoca_project_2025.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.enoca_project_2025.DTO.OrderItemDTO;
import com.example.enoca_project_2025.entity.OrderItem;

public class OrderItemMapper {
    public static List<OrderItemDTO> toDTOList(List<OrderItem> orderItems) {
        if (orderItems == null) {
            return null;
        }
        return orderItems.stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static OrderItemDTO toDTO(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setProduct(ProductMapper.toDTO(orderItem.getProduct()));
        dto.setOrderQuantity(orderItem.getOrderQuantity());
        dto.setOrderPrice(orderItem.getOrderPrice());
        return dto;
    }
}
