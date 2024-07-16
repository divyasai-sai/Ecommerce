package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Order;
import com.example.model.OrderCreationRequestDTO;
import com.example.model.OrderItem;
import com.example.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public Order createAndProcessOrder(@RequestBody OrderCreationRequestDTO orderCreationRequestDTO) {
        return orderService.createAndProcessOrder(orderCreationRequestDTO.getOrder(),
                orderCreationRequestDTO.getSelectedItems());
    }

    @GetMapping("/history/{userId}")
    public List<Order> getOrderHistory(@PathVariable("userId") Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("/items/{orderId}")
    public List<OrderItem> getOrderItems(@PathVariable("orderId") Long orderId) {
        return orderService.getOrderItemsByOrderId(orderId);
    }
}
