package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.CategorySales;
import com.example.model.OrderItem;

public interface SalesRepository extends JpaRepository<OrderItem, Long> {

	@Query("SELECT new com.example.model.CategorySales(c.name, SUM(oi.price * oi.quantity)) " + "FROM OrderItem oi "
			+ "JOIN oi.productVariant pv " + "JOIN pv.product p " + "JOIN p.category c " + "GROUP BY c.name")
	List<CategorySales> findSalesByCategory();
}