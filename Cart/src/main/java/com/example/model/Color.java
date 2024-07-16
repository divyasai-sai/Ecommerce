package com.example.model;
 
import java.util.List;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
 
@Entity
 
public class Color {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	private String color;
 
	@OneToMany(mappedBy = "color", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductVariant> productVariants;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<ProductVariant> getProductVariants() {
		return productVariants;
	}

	public void setProductVariants(List<ProductVariant> productVariants) {
		this.productVariants = productVariants;
	}
 

 
}