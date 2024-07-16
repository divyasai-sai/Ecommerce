package com.example.model;
 
import java.math.BigDecimal;
 
public class CategorySales {
	private String categoryName;
	private BigDecimal totalSales;
 
	public CategorySales(String categoryName, BigDecimal totalSales) {
		this.categoryName = categoryName;
		this.totalSales = totalSales;
	}
 
	// Getters and setters
	public String getCategoryName() {
		return categoryName;
	}
 
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
 
	public BigDecimal getTotalSales() {
		return totalSales;
	}
 
	public void setTotalSales(BigDecimal totalSales) {
		this.totalSales = totalSales;
	}
}