// ToCart.java
package com.example.dto;

public class ToCart {
    private Long userId;
    private Long productId;
    private Long colorId;
    private Long sizeId;
    private int quantity;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() { 
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getColorId() {
		return colorId;
	}
	public void setColorId(Long colorId) {
		this.colorId = colorId;
	}
	public Long getSizeId() {
		return sizeId;
	}
	public void setSizeId(Long sizeId) {
		this.sizeId = sizeId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
