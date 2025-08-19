package com.example.enoca_project_2025.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.example.enoca_project_2025.DTO.CartItemDTO;
import com.example.enoca_project_2025.entity.CartItem;

public class CartItemMapper {
    public static List<CartItemDTO> toDTOList(List<CartItem> cartItems) {
        if (cartItems == null) {
            return null;
        }
        return cartItems.stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public static CartItemDTO toDTO(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        CartItemDTO dto = new CartItemDTO();
        dto.setId(cartItem.getId());
        dto.setProduct(ProductMapper.toDTO(cartItem.getProduct()));
        dto.setQuantity(cartItem.getQuantity());
        return dto;
    }

}
