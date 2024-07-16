package com.example.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemDTOTest {

    @Test
    void testGettersAndSetters() {
        // Create a new CartItemDTO object
        CartItemDTO cartItemDTO = new CartItemDTO();

        // Test id
        Long id = 1L;
        cartItemDTO.setId(id);
        assertEquals(id, cartItemDTO.getId());

        // Test productVariantId 
        Long productVariantId = 101L;
        cartItemDTO.setProductVariantId(productVariantId);
        assertEquals(productVariantId, cartItemDTO.getProductVariantId());

        // Test quantity
        int quantity = 5;
        cartItemDTO.setQuantity(quantity);
        assertEquals(quantity, cartItemDTO.getQuantity());
    }
}
