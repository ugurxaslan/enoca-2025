package com.example.enoca_project_2025.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enoca_project_2025.DTO.CartDTO;
import com.example.enoca_project_2025.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long id) {
        CartDTO cartDTO = cartService.getCart(id);
        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long id) {
        CartDTO updatedCart = cartService.updateCart(id);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{id}/empty")
    public ResponseEntity<CartDTO> emptyCart(@PathVariable Long id) {
        CartDTO cartDTO = cartService.emptyCart(id);
        return ResponseEntity.ok(cartDTO);
    }

    @PutMapping("/{cartId}/add-product/{productId}")
    public ResponseEntity<CartDTO> addProductToCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {

        CartDTO updatedCart = cartService.addProductToCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

    @PutMapping("/{cartId}/remove-product/{productId}")
    public ResponseEntity<CartDTO> removeProductFromCart(
            @PathVariable Long cartId,
            @PathVariable Long productId) {

        CartDTO updatedCart = cartService.removeProductFromCart(cartId, productId);
        return ResponseEntity.ok(updatedCart);
    }

}
