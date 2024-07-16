package com.example.repository;

import com.example.model.CartItem;
import com.example.model.ProductVariant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	CartItem findByCartIdAndProductVariantId(Long cartId, Long productVariantId);
	List<CartItem> findByCartId(Long cartId);
	void deleteByCartId (ProductVariant productVariant);
	void deleteByProductVariant(ProductVariant productVariant);
}
