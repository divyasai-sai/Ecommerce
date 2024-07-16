package com.example.model;
 
import java.math.BigDecimal;
 
import org.hibernate.annotations.OnDelete;

import org.hibernate.annotations.OnDeleteAction;
 
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
 
@Entity
 
public class ProductVariant {
 
	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
 
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "product_id", nullable = false)

	@OnDelete(action = OnDeleteAction.CASCADE)

	private Product product;
 
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "size_id", nullable = false)

	private Size size;
 
	@ManyToOne(fetch = FetchType.LAZY)

	@JoinColumn(name = "color_id", nullable = false)

	private Color color;
 
	private Integer stock;

	private BigDecimal price;
 
	public Long getId() {
 
		return id;
 
	}
 
	public void setId(Long id) {
 
		this.id = id;
 
	}
 
	public Product getProduct() {
 
		return product;
 
	}
 
	public void setProduct(Product product) {
 
		this.product = product;
 
	}
 
	public Size getSize() {
 
		return size;
 
	}
 
	public void setSize(Size size) {
 
		this.size = size;
 
	}
 
	public Color getColor() {
 
		return color;
 
	}
 
	public void setColor(Color color) {
 
		this.color = color;
 
	}
 
	public Integer getStock() {
 
		return stock;
 
	}
 
	public void setStock(Integer stock) {
 
		this.stock = stock;
 
	}
 
	public BigDecimal getPrice() {
 
		return price;
 
	}
 
	public void setPrice(BigDecimal price) {
 
		this.price = price;
 
	}
 
}
