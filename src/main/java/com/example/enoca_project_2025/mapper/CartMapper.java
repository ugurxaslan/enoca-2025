package com.example.enoca_project_2025.mapper;

import com.example.enoca_project_2025.DTO.CartDTO;
import com.example.enoca_project_2025.entity.Cart;

public class CartMapper {
    public static CartDTO toDTO(Cart cart) {
        if (cart == null) {
            return null;
        }
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setTotalPrice(cart.getTotalPrice());
        cartDTO.setCustomer(cart.getCustomer() != null ? CustomerMapper.toDTO(cart.getCustomer()) : null);
        cartDTO.setCartItems(CartItemMapper.toDTOList(cart.getCartItems()));
        return cartDTO;
    }

}
