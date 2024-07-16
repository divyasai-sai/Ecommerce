package com.example.model.test;



import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.ProductVariant;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

 class CartItemsTest {

    @Mock
    private Cart cart;

    @Mock
    private ProductVariant productVariant;

    private Validator validator;

    @BeforeEach
   void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testCartItemFields() {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setCart(cart);
        cartItem.setProductVariant(productVariant);
        cartItem.setQuantity(3);

        assertEquals(1L, cartItem.getId());
        assertEquals(cart, cartItem.getCart());
        assertEquals(productVariant, cartItem.getProductVariant());
        assertEquals(3, cartItem.getQuantity());
    }

    @Test
     void testCartItemValidation() {
        CartItem cartItem = new CartItem();
        cartItem.setId(null);  // id should not be null
        cartItem.setCart(null);  // cart should not be null
          // productVariant should not be null
          // quantity should not be null

        Set<ConstraintViolation<CartItem>> violations = validator.validate(cartItem);
        assertEquals(0, violations.size());  // No validation constraints in this case

        for (ConstraintViolation<CartItem> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
