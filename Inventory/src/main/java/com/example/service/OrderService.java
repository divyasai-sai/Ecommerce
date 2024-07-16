package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Order;
import com.example.model.OrderItem;
import com.example.model.ProductDetailsDTO;
import com.example.model.ProductVariant;
import com.example.repository.CartItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProductVariantRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductVariantRepository productVariantRepository;

	@Transactional
	public Order createAndProcessOrder(Order order, List<ProductDetailsDTO> selectedItems) {
		// Check stock availability
		for (ProductDetailsDTO productDetails : selectedItems) {
			ProductVariant productVariant = productVariantRepository.findById(productDetails.getVariantId())
					.orElseThrow(() -> new IllegalArgumentException("Product variant not found"));
			if (productVariant.getStock() < productDetails.getQuantity()) {
				throw new IllegalArgumentException(
						"Insufficient stock for product: " + productDetails.getProductName());
			}
		}

		// Save the order
		Order savedOrder = orderRepository.save(order);

		// Create order items and update stock
		for (ProductDetailsDTO productDetails : selectedItems) {
			ProductVariant productVariant = productVariantRepository.findById(productDetails.getVariantId())
					.orElseThrow(() -> new IllegalArgumentException("Product variant not found"));

			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(savedOrder);
			orderItem.setProductVariant(productVariant);
			orderItem.setQuantity(productDetails.getQuantity());
			orderItem.setPrice(productDetails.getPrice());
			orderItemRepository.save(orderItem);

			// Update stock in ProductVariant
			productVariant.setStock(productVariant.getStock() - productDetails.getQuantity());
			productVariantRepository.save(productVariant);

			// Optionally remove cart item
			cartItemRepository.deleteByProductVariant(productVariant);
		}

		// Update order status
		savedOrder.setStatus("Completed");
		orderRepository.save(savedOrder);

		return savedOrder;
	}

	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
		return orderItemRepository.findOrderItemsWithProductNameByOrderId(orderId);
	}

	// Method to create a new order without processing (for testing purposes or
	// other uses)
//	public Order createOrder(Order order) {
//		return orderRepository.save(order);
//	}

	public List<Order> getOrdersByUserId(Long userId) {
		return orderRepository.findByUserId(userId);
	}

//	public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
//		return orderItemRepository.findByOrderId(orderId);
//	}
}
