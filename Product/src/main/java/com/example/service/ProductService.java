package com.example.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.model.Product;
import com.example.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Object[]> getAll() {
		return productRepository.findAllagain();
	}

	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<Product> getProductsByCategory(long categoryId, Pageable pageable) {
		return productRepository.findByCategoryId(categoryId, pageable);
	}

	public Page<Product> getProductsByColor(Long colorId, Pageable pageable) {
		return productRepository.filterByColor(colorId, pageable);
	}

	public Page<Product> getProductsBySize(Long sizeId, Pageable pageable) {
		return productRepository.filterBySize(sizeId, pageable);
	}

	public Page<Product> getProductsByColorAndSize(Long colorId, Long sizeId, Pageable pageable) {
		return productRepository.filterByColorAndSize(colorId, sizeId, pageable);
	}

	public Page<Product> getProductsByColorcat(Long categoryId, Long colorId, Pageable pageable) {
		return productRepository.filterByColorcat(categoryId, colorId, pageable);
	}

	public Optional<Product> getProductById(Long prodId) {
		return productRepository.findById(prodId);
	}

	public Page<Product> getProductsBySizecat(Long categoryId, Long sizeId, Pageable pageable) {
		return productRepository.filterBySizecat(categoryId, sizeId, pageable);
	}

	public Page<Product> getProductsByColorAndSizecat(Long categoryId, Long colorId, Long sizeId, Pageable pageable) {
		return productRepository.filterByColorAndSizecat(categoryId, colorId, sizeId, pageable);
	}
}
