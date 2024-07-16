package com.example.dto;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDTOTest {

    @Test
   void testGettersAndSetters() {
        // Create a new CartDTO object
        CartDTO cartDTO = new CartDTO();

        // Test id
        Long id = 1L;
        cartDTO.setId(id);
        assertEquals(id, cartDTO.getId());
 
        // Test userId
        Long userId = 2L;
        cartDTO.setUserId(userId);
        assertEquals(userId, cartDTO.getUserId());

        // Test items
        List<CartItemDTO> items = new ArrayList<>();
        CartItemDTO item1 = new CartItemDTO();
        item1.setId(1L);
        item1.setProductVariantId(101L);
        item1.setQuantity(2);
        items.add(item1);
        
        CartItemDTO item2 = new CartItemDTO();
        item2.setId(2L);
        item2.setProductVariantId(102L);
        item2.setQuantity(3);
        items.add(item2);

        cartDTO.setItems(items);
        assertEquals(items, cartDTO.getItems());
        assertEquals(2, cartDTO.getItems().size());
        assertEquals(1L, cartDTO.getItems().get(0).getId());
        assertEquals(101L, cartDTO.getItems().get(0).getProductVariantId());
        assertEquals(2, cartDTO.getItems().get(0).getQuantity());
        assertEquals(2L, cartDTO.getItems().get(1).getId());
        assertEquals(102L, cartDTO.getItems().get(1).getProductVariantId());
        assertEquals(3, cartDTO.getItems().get(1).getQuantity());
    }
}
