package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity

public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_id")
	@JsonBackReference
	private Cart cart;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_variant_id")
	private ProductVariant productVariant;
	
	

	private int quantity;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public Cart getCart() {

		return cart;

	}

	public void setCart(Cart cart) {

		this.cart = cart;

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

}
