package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.model.CategorySales;
import com.example.model.Color;
import com.example.model.ProductVariant;
import com.example.model.SearchRequest;
import com.example.model.Size;
import com.example.repository.CategoryRepository;
import com.example.repository.ColorRepository;
import com.example.repository.SizeRepository;
import com.example.service.ProductVariantService;
import com.example.service.SalesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productVariant")
public class ProductVariantController {

	@Autowired
	private ProductVariantService productVariantService;

	@Autowired
	private SalesService salesService;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private SizeRepository sizeRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/{id}")
	public ProductVariant getProductVariantById(@PathVariable Long id) {
		return productVariantService.findById(id);
	}

	@PatchMapping("/updatequant/{pvId}")
	public void updateQuant(@PathVariable("pvId") long pvId, @RequestBody Map<String, Integer> stockUpdate) {
		int newStock = stockUpdate.get("stock");
		productVariantService.updateQuantity(pvId, newStock);
	}

	@GetMapping("/display")
	public List<ProductVariant> getAll() {
		return productVariantService.getAllProducts();
	}

	@PostMapping("/add")
	public void addProductVariant(@Valid @RequestBody ProductVariant productVariant) {
		productVariantService.addOrUpdate(productVariant);
	}

	@DeleteMapping("/delete/{pvId}")
	public void deleteProductVariant(@PathVariable("pvId") long pvId) {
		productVariantService.delete(pvId);
	}

	@PostMapping("/search")
	public ResponseEntity<?> searchProductVariants(@RequestBody SearchRequest searchRequest) {
		try {
			List<ProductVariant> productVariants = productVariantService.searchProductVariants(
					searchRequest.getCategory(), searchRequest.getColor(), searchRequest.getSize());
			return new ResponseEntity<>(productVariants, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception with a logger
			Logger logger = LoggerFactory.getLogger(ProductVariantController.class);
			logger.error("Error during search", e);

			// Return a response with a 500 status and error message
			return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/sales")
	public List<CategorySales> getSalesByCategory() {
		return salesService.getSalesByCategory();
	}

	@GetMapping("/top5ProductsOfMonth")
	public ResponseEntity<List<Object[]>> getTop5ProductsOfTheMonth() {
		LocalDateTime startDate = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0)
				.withNano(0);
		LocalDateTime endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999999);
		List<Object[]> topProducts = productVariantService.getTop5ProductsOfTheMonth(startDate, endDate);
		return new ResponseEntity<>(topProducts, HttpStatus.OK);
	}

	@GetMapping("/colors")
	public List<Color> getAllColors() {
		return colorRepository.findAll();
	}

	@GetMapping("/sizes")
	public List<Size> getAllSize() {
		return sizeRepository.findAll();
	}

	@GetMapping("/category")
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
}
