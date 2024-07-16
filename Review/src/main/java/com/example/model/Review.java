package com.example.model;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity

public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	private Integer rating;
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

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

	public User getUser() {

		return user;

	}

	public void setUser(User user) {

		this.user = user;

	}

	public Integer getRating() {

		return rating;

	}

	public void setRating(Integer rating) {

		this.rating = rating;

	}

	public String getComment() {

		return comment;

	}

	public void setComment(String comment) {

		this.comment = comment;

	}

	public Date getCreatedAt() {

		return createdAt;

	}

	public void setCreatedAt(Date createdAt) {

		this.createdAt = createdAt;

	}

}