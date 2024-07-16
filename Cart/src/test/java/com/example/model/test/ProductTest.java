package com.example.model.test;



import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Category;
import com.example.model.Product;
import com.example.model.ProductVariant;
import com.example.model.Review;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class ProductTest {

    @Mock
    private List<Review> reviews;

    @Mock
    private Category category;

    @Mock
    private List<ProductVariant> productVariants;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testProductFields() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("This is a test product");
//        product.setReviews(reviews);
        product.setCategory(category);
        product.setProductVariants(productVariants);

        assertEquals(1L, product.getId());
        assertEquals("Test Product", product.getName());
        assertEquals("This is a test product", product.getDescription());
        
    }

   
}

