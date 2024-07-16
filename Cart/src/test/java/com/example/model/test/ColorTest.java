package com.example.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Color;
import com.example.model.ProductVariant;

class ColorTest {

    private Color color;
    private List<ProductVariant> productVariants;

    @BeforeEach
     void setUp() {
        color = new Color();
        color.setId(1L);
        color.setColor("Red");

        productVariants = new ArrayList<>();
        productVariants.add(mock(ProductVariant.class));
        color.setProductVariants(productVariants);
    }

    @Test 
     void testColorFields() {
        assertEquals(1L, color.getId());
        assertEquals("Red", color.getColor());
        assertNotNull(color.getProductVariants());
        assertEquals(1, color.getProductVariants().size());
        assertEquals(productVariants.get(0), color.getProductVariants().get(0));
    }

    @Test
     void testSetters() {
        List<ProductVariant> newProductVariants = new ArrayList<>();
        newProductVariants.add(mock(ProductVariant.class));
        color.setProductVariants(newProductVariants);
        assertEquals(newProductVariants, color.getProductVariants());

        color.setColor("Blue");
        assertEquals("Blue", color.getColor());
    }
}
