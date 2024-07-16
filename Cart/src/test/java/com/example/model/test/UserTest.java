package com.example.model.test;

import org.junit.jupiter.api.Test;

import com.example.model.Cart;
import com.example.model.Order;
import com.example.model.Review;
import com.example.model.User;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User user = new User();

        // Test ID
        user.setId(1L);
        assertEquals(1L, user.getId());

        // Test First Name
        user.setFirstName("John");
        assertEquals("John", user.getFirstName());

        // Test Last Name
        user.setLastName("Doe");
        assertEquals("Doe", user.getLastName());

        // Test Email
        user.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", user.getEmail());

        // Test Password
        user.setPassword("password123");
        assertEquals("password123", user.getPassword());

        // Test Address
        user.setAddress("123 Street Name");
        assertEquals("123 Street Name", user.getAddress());

        // Test Phone Number
        user.setPhoneNo("1234567890");
        assertEquals("1234567890", user.getPhoneNo());

        // Test Security Question
        user.setSecurityQuestion("What is your pet's name?");
        assertEquals("What is your pet's name?", user.getSecurityQuestion());

        // Test Security Answer
        user.setSecurityAnswer("Fluffy");
        assertEquals("Fluffy", user.getSecurityAnswer());

        // Test Role
        user.setRole("USER");
        assertEquals("USER", user.getRole());

        // Test Carts 
        List<Cart> carts = Collections.singletonList(new Cart());
        user.setCarts(carts);
        assertEquals(carts, user.getCarts());

        // Test Orders
        List<Order> orders = Collections.singletonList(new Order());
        user.setOrders(orders);
        assertEquals(orders, user.getOrders());

        // Test Reviews
        List<Review> reviews = Collections.singletonList(new Review());
        user.setReviews(reviews);
        assertEquals(reviews, user.getReviews());
    }
}
