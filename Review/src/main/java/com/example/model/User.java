package com.example.model;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	@NotBlank(message = "First name is required")
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 50)
	@NotBlank(message = "Last name is required")
	private String lastName;

	@Column(name = "EMAIL", nullable = false, unique = true, length = 100)
	@Email(message = "Invalid email format")
	@NotBlank(message = "Email is required")
	private String email;

	@Column(name = "PASSWORD", nullable = false, length = 255)
	@Size(min = 8, message = "Password must be at least 8 characters")
	@NotBlank(message = "Password is required")
	private String password;

	@Column(name = "PHONE_NO", length = 15)
	@Pattern(regexp = "^[0-9]+$", message = "Must be only digits")
	@Size(min = 10, message = "Phone number must be at least 10 digits")
	@NotBlank(message = "Phone number is required")
	private String phoneNo;

	@Column(name = "SECURITY_QUESTION", nullable = false, length = 255)
	@NotBlank(message = "Security question is required")
	private String securityQuestion;

	@Column(name = "SECURITY_ANSWER", nullable = false, length = 255)
	@NotBlank(message = "Security answer is required")
	private String securityAnswer;

	// @JsonBackReference(value = "user-carts")
	@JsonIgnore	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cart> carts;

	// @JsonBackReference(value = "user-orders")
	@JsonIgnore	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Order> orders;

	// @JsonBackReference(value = "user-reviews")
	@JsonIgnore
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Review> reviews;

	@Column(name = "ROLE", nullable = false, length = 50)
	private String role;

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@JsonIgnore
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

//	@JsonIgnore
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

//	@JsonIgnore
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@JsonIgnore
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

}
