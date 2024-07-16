package com.example.model;
 
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
 
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
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	private Cart cart;
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_variant_id", nullable = false)
	private ProductVariant productVariant;
 
	private Integer quantity;
 
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

