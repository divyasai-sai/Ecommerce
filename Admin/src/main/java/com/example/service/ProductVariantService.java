package com.example.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Color;
import com.example.model.Product;
import com.example.model.ProductVariant;
import com.example.model.Size;
import com.example.repository.ColorRepository;
import com.example.repository.ProductRepository;
import com.example.repository.ProductVariantRepository;
import com.example.repository.SizeRepository;
 
@Service
public class ProductVariantService {
 
    @Autowired
    private ProductRepository productRepository;
 
    @Autowired
    private ColorRepository colorRepository;
 
    @Autowired
    private SizeRepository sizeRepository;
 
    @Autowired
    private ProductVariantRepository productVariantRepository;
 
    public ProductVariant findById(Long id) {
        return productVariantRepository.findById(id).orElse(null);
    }
 
    public ProductVariant save(ProductVariant productVariant) {
        return productVariantRepository.save(productVariant);
    }
 
    public Product add(Product prod) {
        productRepository.save(prod);
        return prod;
    }
 
    public void addOrUpdate(ProductVariant productVariant) {
        Product product = productVariant.getProduct();
        if (product.getId() == null) {
            product = add(product);
            productVariant.setProduct(product);
        }
 
        Color color = productVariant.getColor();
        if (color.getId() == null) {
            color = colorRepository.save(color);
            productVariant.setColor(color);
        }
 
        Size size = productVariant.getSize();
        if (size.getId() == null) {
            size = sizeRepository.save(size);
            productVariant.setSize(size);
        }
 
        productVariantRepository.save(productVariant);
    }
 
    public void updateQuantity(long pvId, int num) {
        Optional<ProductVariant> productVariantRepositoryOld = productVariantRepository.findById(pvId);
        ProductVariant productVariant = productVariantRepositoryOld
                .orElseThrow(() -> new IllegalArgumentException("Product variant not found"));
        productVariant.setStock(num);
        productVariantRepository.save(productVariant);
    }
 
    public List<ProductVariant> getAllProducts() {
        return productVariantRepository.findAll();
    }
 
    public void updatePrice(long pvId, BigDecimal p) {
        Optional<ProductVariant> productVariantRepositoryOld = productVariantRepository.findById(pvId);
        ProductVariant productVariant = productVariantRepositoryOld
                .orElseThrow(() -> new IllegalArgumentException("Product variant not found"));
        productVariant.setPrice(p);
        productVariantRepository.save(productVariant);
    }
 
    public void delete(long pvId) {
        productVariantRepository.deleteById(pvId);
    }
 
    public Optional<ProductVariant> pvById(long pvId) {
        return productVariantRepository.findById(pvId);
    }
 
    public List<ProductVariant> searchProductVariants(String category, String color, String size) {
        return productVariantRepository.searchProductVariants(category, color, size);
    }
    
    
    public List<Object[]> getTop5ProductsOfTheMonth(LocalDateTime startDate, LocalDateTime endDate) {
        return productVariantRepository.findTop5ProductsOfTheMonth(startDate, endDate);
    }
}