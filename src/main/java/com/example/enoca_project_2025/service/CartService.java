package com.example.enoca_project_2025.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.enoca_project_2025.DTO.CartDTO;
import com.example.enoca_project_2025.entity.Cart;
import com.example.enoca_project_2025.entity.CartItem;
import com.example.enoca_project_2025.entity.Product;
import com.example.enoca_project_2025.exception.customException.CartNotFoundException;
import com.example.enoca_project_2025.exception.customException.OutOfStockException;
import com.example.enoca_project_2025.exception.customException.ProductNotFoundException;
import com.example.enoca_project_2025.mapper.CartMapper;
import com.example.enoca_project_2025.repository.CartRepository;
import com.example.enoca_project_2025.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CartDTO getCart(Long id) {// okey
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        return CartMapper.toDTO(cart);
    }

    @Transactional
    public CartDTO updateCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));

        cart.getCartItems().removeIf(cartItem -> {
            Product product = cartItem.getProduct();
            if (product.getStockQuantity() == 0) {
                return true;
            } else if (cartItem.getQuantity() > product.getStockQuantity()) {
                cartItem.setQuantity(product.getStockQuantity());
            }
            return false;
        });

        cart.calculateTotalPrice();
        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toDTO(updatedCart);
    }

    @Transactional
    public CartDTO emptyCart(Long id) {// okey
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException(id));
        cart.getCartItems().clear();
        cart.calculateTotalPrice();
        cartRepository.save(cart);
        return CartMapper.toDTO(cart);
    }

    @Transactional
    public CartDTO addProductToCart(Long cartId, Long productId) {// okey
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        Optional<CartItem> existingItemOpt = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            CartItem existingItem = existingItemOpt.get();
            if (existingItem.getQuantity() + 1 > product.getStockQuantity()) {
                throw new OutOfStockException(productId);
            }
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            if (product.getStockQuantity() < 1) {
                throw new OutOfStockException(productId);
            }
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(1);
            cart.getCartItems().add(newItem);
        }

        cart.calculateTotalPrice();
        Cart savedCart = cartRepository.save(cart);

        return CartMapper.toDTO(savedCart);
    }

    @Transactional
    public CartDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        CartItem cartItem = cart.getCartItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(productId));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
        } else {
            cart.getCartItems().remove(cartItem);
        }

        cart.calculateTotalPrice();

        Cart savedCart = cartRepository.save(cart);
        return CartMapper.toDTO(savedCart);
    }

}
