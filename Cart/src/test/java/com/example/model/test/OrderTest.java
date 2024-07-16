package com.example.model.test;


import static org.junit.jupiter.api.Assertions.assertEquals;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.User;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

 class OrderTest {

    @Mock
    private User user;

    @Mock
    private List<OrderItem> orderItems;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testOrderFields() {
        Order order = new Order();
        order.setId(1L);
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setTotalAmount(new BigDecimal("100.00"));
        order.setShippingAddress("123 Main St");
        order.setStatus("Pending");
        

        assertEquals(1L, order.getId());
        assertEquals(user, order.getUser());
        assertEquals("100.00", order.getTotalAmount().toPlainString());
        assertEquals("123 Main St", order.getShippingAddress());
        assertEquals("Pending", order.getStatus());
        
    }

   
}
