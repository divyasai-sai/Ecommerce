package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.exception.ResourceNotFoundException;
import com.example.model.Color;
import com.example.model.Product;
import com.example.model.Review;
import com.example.model.Size;
import com.example.repository.ColorRepository;
import com.example.repository.ReviewRepository;
import com.example.repository.SizeRepository;
import com.example.service.ProductService;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private SizeRepository sizeRepository;

    @Mock
    private ColorRepository colorRepository;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productService.getAllProducts(pageable)).thenReturn(expected);
        Page<Product> result = productController.getAllProducts(null, null, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsByColor(1L, pageable)).thenReturn(expected);
        result = productController.getAllProducts(1L, null, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsBySize(1L, pageable)).thenReturn(expected);
        result = productController.getAllProducts(null, 1L, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsByColorAndSize(1L, 1L, pageable)).thenReturn(expected);
        result = productController.getAllProducts(1L, 1L, 0, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByCategory() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productService.getProductsByCategory(1L, pageable)).thenReturn(expected);
        Page<Product> result = productController.getProductsByCategory(1L, null, null, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsByColorcat(1L, 1L, pageable)).thenReturn(expected);
        result = productController.getProductsByCategory(1L, 1L, null, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsBySizecat(1L, 1L, pageable)).thenReturn(expected);
        result = productController.getProductsByCategory(1L, null, 1L, 0, 10);
        assertEquals(expected, result);

        when(productService.getProductsByColorAndSizecat(1L, 1L, 1L, pageable)).thenReturn(expected);
        result = productController.getProductsByCategory(1L, 1L, 1L, 0, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testGetReviewsByProductId() {
        List<Review> expected = List.of(new Review());
        when(reviewRepository.findByProductId(1L)).thenReturn(expected);
        List<Review> result = productController.getReviewsByProductId(1L);
        assertEquals(expected, result);

        when(reviewRepository.findByProductId(1L)).thenReturn(List.of());
        try {
            productController.getReviewsByProductId(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Reviews not found for product ID: 1", e.getMessage());
        }
    }

    @Test
    public void testGetAllColors() {
        List<Color> expected = List.of(new Color());
        when(colorRepository.findAll()).thenReturn(expected);
        List<Color> result = productController.getAllColors();
        assertEquals(expected, result);
    }

    @Test
    public void testGetAllSize() {
        List<Size> expected = List.of(new Size());
        when(sizeRepository.findAll()).thenReturn(expected);
        List<Size> result = productController.getAllSize();
        assertEquals(expected, result);
    }

 

    @Test
    public void testGetProductById() {
        Product expected = new Product();
        when(productService.getProductById(1L)).thenReturn(Optional.of(expected));
        Product result = productController.getProductById(1L);
        assertEquals(expected, result);

        when(productService.getProductById(1L)).thenReturn(Optional.empty());
        try {
            productController.getProductById(1L);
        } catch (ResourceNotFoundException e) {
            assertEquals("Product not found with ID: 1", e.getMessage());
        }
    }
}
