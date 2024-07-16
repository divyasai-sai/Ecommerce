package com.example.controller;

import com.example.dto.ProductDetailsDTO;
import com.example.dto.RemoveFromCartRequest;
import com.example.dto.ToCart;
import com.example.dto.UpdateQuantityRequest;
import com.example.model.ProductVariant;
import com.example.model.User;
import com.example.repository.ProductVariantRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CartControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartService cartService;

    @Mock
    private ProductVariantRepository productVariantRepository;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCart() {
        List<ProductDetailsDTO> cartDTO = Collections.singletonList(new ProductDetailsDTO());
        when(cartService.getCartByUserId(anyLong())).thenReturn(cartDTO);

        ResponseEntity<List<ProductDetailsDTO>> response = cartController.getCart(1L);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals(cartDTO, response.getBody());
    }

    @Test
    void testGetUserIdByEmail() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findByEmail(anyString())).thenReturn(user);

        ResponseEntity<Long> response = cartController.getUserIdByEmail("test@example.com");

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals(1L, response.getBody());
    }

    @Test
    void testAddToCart() {
        ToCart toCart = new ToCart();
        toCart.setUserId(1L);
        toCart.setProductId(1L);
        toCart.setColorId(1L);
        toCart.setSizeId(1L);
        toCart.setQuantity(1);

        when(cartService.findByProdIdColorIdSizeId(anyLong(), anyLong(), anyLong())).thenReturn(1L);
        when(productVariantRepository.findById(anyLong())).thenReturn(Optional.of(new ProductVariant()));

        ResponseEntity<String> response = cartController.addToCart(toCart);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals("Product added to cart successfully", response.getBody());
    }

    @Test
    void testRemoveFromCart() {
        RemoveFromCartRequest request = new RemoveFromCartRequest();
        request.setUserId(1L);
        request.setProductVariantId(1L);

        doNothing().when(cartService).removeFromCart(anyLong(), anyLong());

        ResponseEntity<String> response = cartController.removeFromCart(request);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals("Product removed from cart successfully", response.getBody());
    }

    @Test
    void testUpdateQuantity() {
        UpdateQuantityRequest request = new UpdateQuantityRequest();
        request.setUserId(1L);
        request.setProductVariantId(1L);
        request.setQuantity(2);

      //  doNothing().when(cartService).updateQuantity(anyLong(), anyLong(), anyInt());

        ResponseEntity<String> response = cartController.updateQuantity(request);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals("Quantity updated successfully", response.getBody());
    }

    @Test
    void testGetCartSummary() {
        BigDecimal summary = BigDecimal.valueOf(100);
        when(cartService.getCartSummary(anyLong())).thenReturn(summary);

        ResponseEntity<BigDecimal> response = cartController.getCartSummary(1L);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals(summary, response.getBody()); 
    }

    @Test
    void testCheckStock() {
        when(cartService.checkStock(anyLong())).thenReturn(true);

        ResponseEntity<Boolean> response = cartController.checkStock(1L);

        assertEquals(HttpStatus.SC_OK, response.getStatusCodeValue());
        assertEquals(true, response.getBody());
    }
}
