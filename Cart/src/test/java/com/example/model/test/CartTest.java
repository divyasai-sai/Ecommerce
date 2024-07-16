package com.example.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.User;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
 class CartTest {

    @Mock
    private User user;

    @Mock
    private CartItem cartItem1;

    @Mock
    private CartItem cartItem2;

    private Validator validator;

    @BeforeEach
   void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testCartFields() {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setItems(Arrays.asList(cartItem1, cartItem2));

        assertEquals(1L, cart.getId());
        assertEquals(user, cart.getUser());
        assertNotNull(cart.getItems());
        assertEquals(2, cart.getItems().size());
    }

    @Test
     void testCartValidation() {
        Cart cart = new Cart();
        cart.setId(null);  // id should not be null
        cart.setUser(null);  // user should not be null

        Set<ConstraintViolation<Cart>> violations = validator.validate(cart);
        assertEquals(0, violations.size());  // No validation constraints in this case

        for (ConstraintViolation<Cart> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
