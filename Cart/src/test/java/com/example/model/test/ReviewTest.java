package com.example.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Product;
import com.example.model.Review;
import com.example.model.User;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class ReviewTest {

    @Mock
    private Product product;

    @Mock
    private User user;

    private Validator validator;

    @BeforeEach
  void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testReviewFields() {
        Review review = new Review();
        review.setId(1L);
        review.setProduct(product);
        review.setUser(user);
        review.setRating(5);
        review.setComment("Great product!");
        review.setCreatedAt(new Date());

        assertEquals(1L, review.getId());
        assertEquals(product, review.getProduct());
        assertEquals(user, review.getUser());
        assertEquals(5, review.getRating());
        assertEquals("Great product!", review.getComment());
        assertNotNull(review.getCreatedAt());
    }

    
}
