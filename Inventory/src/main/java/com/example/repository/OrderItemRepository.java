package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findByOrderId(Long orderId);

	@Query("SELECT oi FROM OrderItem oi " + "JOIN FETCH oi.productVariant pv " + "JOIN FETCH pv.product p "
			+ "WHERE oi.order.id = :orderId")
	List<OrderItem> findOrderItemsWithProductNameByOrderId(Long orderId);
}