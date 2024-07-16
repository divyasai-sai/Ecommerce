package com.example.model;
 
import java.util.List;

 
import com.fasterxml.jackson.annotation.JsonManagedReference;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
 
@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User user;
 
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<CartItem> items;
 
	// Getters and Setters
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	public List<CartItem> getItems() {
		return items;
	}
 
	public void setItems(List<CartItem> items) {
		this.items = items;
	}
}