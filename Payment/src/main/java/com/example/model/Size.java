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
 
public class Size {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	private String size;
 
	@OneToMany(mappedBy = "size", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProductVariant> productVariants;
 
	public Long getId() {
 
		return id;
 
	}
 
	public void setId(Long id) {
 
		this.id = id;
 
	}
 
	public String getSize() {
 
		return size;
 
	}
 
	public void setSize(String size) {
 
		this.size = size;
 
	}
 
}