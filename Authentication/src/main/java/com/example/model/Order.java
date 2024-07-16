package com.example.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity

@Table(name = "`Order`")

public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	private BigDecimal totalAmount;
	private String shippingAddress;
	private String status;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {

		this.totalAmount = totalAmount;

	}

	public String getShippingAddress() {

		return shippingAddress;

	}

	public void setShippingAddress(String shippingAddress) {

		this.shippingAddress = shippingAddress;

	}

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}

}