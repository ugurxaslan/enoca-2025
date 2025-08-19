package com.example.enoca_project_2025.mapper;

import com.example.enoca_project_2025.DTO.OrderDTO;
import com.example.enoca_project_2025.entity.Order;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderCode(order.getOrderCode());
        orderDTO.setTotalPrice(order.getTotalPrice());
        orderDTO.setCustomer(CustomerMapper.toDTO(order.getCustomer()));
        orderDTO.setOrderItems(OrderItemMapper.toDTOList(order.getOrderItems()));

        return orderDTO;
    }
}
