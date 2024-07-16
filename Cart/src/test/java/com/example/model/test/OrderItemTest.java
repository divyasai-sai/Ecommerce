package com.example.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.ProductVariant;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class OrderItemTest {

    @Mock
    private Order order;

    @Mock
    private ProductVariant productVariant;

    private Validator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testOrderItemFields() {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(1L);
        orderItem.setOrder(order);
        orderItem.setProductVariant(productVariant);
        orderItem.setQuantity(2);
        orderItem.setPrice(new BigDecimal("29.99"));

        assertEquals(1L, orderItem.getId());
        assertEquals(order, orderItem.getOrder());
        assertEquals(productVariant, orderItem.getProductVariant());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(new BigDecimal("29.99"), orderItem.getPrice());
    }

    @Test
     void testOrderItemValidation() {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(-1);  // Invalid quantity
        orderItem.setPrice(new BigDecimal("-1.00"));  // Invalid price

        Set<ConstraintViolation<OrderItem>> violations = validator.validate(orderItem);
        assertEquals(0, violations.size());  // No violations expected in this case

        for (ConstraintViolation<OrderItem> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}
