package com.example.model;
 
import java.util.List;
 
//Create a DTO class for order creation request
public class OrderCreationRequestDTO {
private Order order;
private List<ProductDetailsDTO> selectedItems;

// Getters and setters
public Order getOrder() {
     return order;
}
 
public void setOrder(Order order) {
     this.order = order;
}
 
public List<ProductDetailsDTO> getSelectedItems() {
     return selectedItems;
}
 
public void setSelectedItems(List<ProductDetailsDTO> selectedItems) {
     this.selectedItems = selectedItems;
}
}