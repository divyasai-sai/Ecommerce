package com.example.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;
	private Long userId;
	private Double amount;
	private String status;
	private LocalDateTime paymentDate;
	private String cardNumber;
	private String upiId;
	private String paymentMethod;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
	private Order order;

	public Long getId() {

		return id;

	}

	public void setId(Long id) {

		this.id = id;

	}

	public Long getUserId() {

		return userId;

	}

	public void setUserId(Long userId) {

		this.userId = userId;

	}

	public Order getOrder() {

		return order;

	}

	public void setOrder(Order order) {

		this.order = order;

	}

	public Double getAmount() {

		return amount;

	}

	public void setAmount(Double amount) {

		this.amount = amount;

	}

	public String getStatus() {

		return status;

	}

	public void setStatus(String status) {

		this.status = status;

	}

	public LocalDateTime getPaymentDate() {

		return paymentDate;

	}

	public void setPaymentDate(LocalDateTime paymentDate) {

		this.paymentDate = paymentDate;

	}

	public String getCardNumber() {

		return cardNumber;

	}

	public void setCardNumber(String cardNumber) {

		this.cardNumber = cardNumber;

	}

	public String getUpiId() {

		return upiId;

	}

	public void setUpiId(String upiId) {

		this.upiId = upiId;

	}

	public String getPaymentMethod() {

		return paymentMethod;

	}

	public void setPaymentMethod(String paymentMethod) {

		this.paymentMethod = paymentMethod;

	}

}