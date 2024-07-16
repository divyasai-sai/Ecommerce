package com.example.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;
 
import java.math.BigDecimal;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import com.example.model.Color;
import com.example.model.Product;
import com.example.model.ProductVariant;
import com.example.model.Size;
 
class ProductVariantTest {
 
    private ProductVariant productVariant;
    private Product product;
    private Color color;
    private Size size;
 
    @BeforeEach
    void setUp() {
        productVariant = new ProductVariant();
        product = new Product();
        color = new Color();
        size = new Size();
    }
 
    @Test
     void testId() {
        assertNull(productVariant.getId());
        productVariant.setId(1L);
        assertEquals(1L, productVariant.getId());
    }
 
    @Test
     void testProduct() {
        assertNull(productVariant.getProduct());
        productVariant.setProduct(product);
        assertEquals(product, productVariant.getProduct());
    }
 
    @Test
     void testSize() {
        assertNull(productVariant.getSize());
        productVariant.setSize(size);
        assertEquals(size, productVariant.getSize());
    }
 
    @Test
     void testColor() {
        assertNull(productVariant.getColor());
        productVariant.setColor(color);
        assertEquals(color, productVariant.getColor());
    }
 
    @Test
     void testStock() {
        assertNull(productVariant.getStock());
        productVariant.setStock(10);
        assertEquals(10, productVariant.getStock());
    }
 
    @Test
     void testPrice() {
        assertNull(productVariant.getPrice());
        productVariant.setPrice(new BigDecimal("100.00"));
        assertEquals(new BigDecimal("100.00"), productVariant.getPrice());
    }
 
    @Test
     void testCompleteProductVariant() {
        productVariant.setId(1L);
        productVariant.setProduct(product);
        productVariant.setSize(size);
        productVariant.setColor(color);
        productVariant.setStock(10);
        productVariant.setPrice(new BigDecimal("100.00"));
 
        assertEquals(1L, productVariant.getId());
        assertEquals(product, productVariant.getProduct());
        assertEquals(size, productVariant.getSize());
        assertEquals(color, productVariant.getColor());
        assertEquals(10, productVariant.getStock());
        assertEquals(new BigDecimal("100.00"), productVariant.getPrice());
    }
}