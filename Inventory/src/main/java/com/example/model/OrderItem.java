package com.example.model;

import java.math.BigDecimal;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
//	@JsonBackReference
	private Order order;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_variant_id", nullable = false)
//	@JsonManagedReference
	private ProductVariant productVariant;

	private Integer quantity;
	private BigDecimal price;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public Order getOrder() {

		return order;

	}

	public void setOrder(Order order) {

		this.order = order;

	}

	public ProductVariant getProductVariant() {

		return productVariant;

	}

	public void setProductVariant(ProductVariant productVariant) {

		this.productVariant = productVariant;

	}

	public Integer getQuantity() {

		return quantity;

	}

	public void setQuantity(Integer quantity) {

		this.quantity = quantity;

	}

	public BigDecimal getPrice() {

		return price;

	}

	public void setPrice(BigDecimal price) {

		this.price = price;

	}

}