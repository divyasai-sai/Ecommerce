package com.example.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Color;
import com.example.model.Product;
import com.example.model.Review;
import com.example.model.Size;
import com.example.repository.ColorRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.SizeRepository;
import com.example.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final ReviewRepository reviewRepository;
	private final SizeRepository sizeRepository;
	private final ColorRepository colorRepository;

	public ProductController(ProductService productService, ReviewRepository reviewRepository,
			SizeRepository sizeRepository, ColorRepository colorRepository) {
		this.productService = productService;
		this.reviewRepository = reviewRepository;
		this.sizeRepository = sizeRepository;
		this.colorRepository = colorRepository;
	}

	@GetMapping("/display")
	public Page<Product> getAllProducts(@RequestParam(required = false, name = "color_id") Long colorid,
			@RequestParam(required = false, name = "size_id") Long sizeid, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);

		if (colorid != null && sizeid != null) {
			return productService.getProductsByColorAndSize(colorid, sizeid, pageable);
		} else if (colorid != null) {
			return productService.getProductsByColor(colorid, pageable);
		} else if (sizeid != null) {
			return productService.getProductsBySize(sizeid, pageable);
		} else {
			return productService.getAllProducts(pageable);
		}
	}

	@GetMapping("/category/{category_id}")
	public Page<Product> getProductsByCategory(@PathVariable("category_id") long categoryId,
			@RequestParam(required = false, name = "color_id") Long colorid,
			@RequestParam(required = false, name = "size_id") Long sizeid, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);

		if (colorid != null && sizeid != null) {
			return productService.getProductsByColorAndSizecat(categoryId, colorid, sizeid, pageable);
		} else if (colorid != null) {
			return productService.getProductsByColorcat(categoryId, colorid, pageable);
		} else if (sizeid != null) {
			return productService.getProductsBySizecat(categoryId, sizeid, pageable);
		} else {
			return productService.getProductsByCategory(categoryId, pageable);
		}
	}

	@GetMapping("/reviews/{productId}")
	public List<Review> getReviewsByProductId(@PathVariable("productId") Long productId) {
		List<Review> reviews = reviewRepository.findByProductId(productId);
		if (reviews.isEmpty()) {
			throw new ResourceNotFoundException("Reviews not found for product ID: " + productId);
		}
		return reviews;
	}

	// Color-related endpoints

	@GetMapping("/colors")
	public List<Color> getAllColors() {
		return colorRepository.findAll();
	}

	@GetMapping("/data")
	public List<Object[]> getAll() {
		return productService.getAll();
	}

	@GetMapping("/sizes")
	public List<Size> getAllSize() {
		return sizeRepository.findAll();
	}

	@GetMapping("/display/{prodId}")
	public Product getProductById(@PathVariable("prodId") long prodId) {
		return productService.getProductById(prodId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + prodId));
	}
}
