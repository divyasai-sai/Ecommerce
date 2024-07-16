package com.example.dto;
 
public class CartItemDTO {
	private Long id;
	private Long productVariantId;
	private int quantity;
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public Long getProductVariantId() {
		return productVariantId;
	}
 
	public void setProductVariantId(Long productVariantId) {
		this.productVariantId = productVariantId;
	}
 
	public int getQuantity() {
		return quantity;
	}
 
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
 
	// Getters and Setters
}
 