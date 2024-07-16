package com.example.model.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.model.Order;
import com.example.model.Transaction;

class TransactionsTest {

    private Transaction transaction;
    private Order order;

    @BeforeEach
    void setUp() {
        order = mock(Order.class);
        transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUserId(100L);
        transaction.setAmount(500.0);
        transaction.setStatus("Completed");
        transaction.setPaymentDate(LocalDateTime.now());
        transaction.setCardNumber("1234-5678-9012-3456");
        transaction.setUpiId("user@upi");
        transaction.setPaymentMethod("Credit Card");
        transaction.setOrder(order);
    }

    @Test
     void testTransactionFields() {
        assertEquals(1L, transaction.getId());
        assertEquals(100L, transaction.getUserId());
        assertEquals(500.0, transaction.getAmount());
        assertEquals("Completed", transaction.getStatus());
        assertNotNull(transaction.getPaymentDate());
        assertEquals("1234-5678-9012-3456", transaction.getCardNumber());
        assertEquals("user@upi", transaction.getUpiId());
        assertEquals("Credit Card", transaction.getPaymentMethod());
        assertEquals(order, transaction.getOrder());
    }

    @Test
     void testSetters() {
        Order newOrder = mock(Order.class);
        transaction.setOrder(newOrder);
        assertEquals(newOrder, transaction.getOrder());

        transaction.setAmount(1000.0);
        assertEquals(1000.0, transaction.getAmount());

        transaction.setStatus("Pending");
        assertEquals("Pending", transaction.getStatus());

        transaction.setPaymentDate(LocalDateTime.of(2023, 1, 1, 12, 0));
        assertEquals(LocalDateTime.of(2023, 1, 1, 12, 0), transaction.getPaymentDate());
    }
}

