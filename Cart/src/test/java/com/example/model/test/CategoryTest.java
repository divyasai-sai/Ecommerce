package com.example.model.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Category;
import com.example.model.Product;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class CategoryTest {

    @Mock
    private Product product;

    private Validator validator;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testCategoryFields() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        List<Product> products = new ArrayList<>();
        products.add(product);
        category.setProducts(products);

        assertEquals(1L, category.getId());
        assertEquals("Electronics", category.getName());
        assertNotNull(category.getProducts());
        assertEquals(1, category.getProducts().size());
        assertTrue(category.getProducts().contains(product));
    }

    
}
