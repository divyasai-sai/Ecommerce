package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import com.example.model.Product;
import com.example.repository.ProductRepository;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   

    @Test
    public void testGetAllProducts() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findAll(pageable)).thenReturn(expected);
        Page<Product> result = productService.getAllProducts(pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByCategory() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findByCategoryId(1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsByCategory(1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByColor() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterByColor(1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsByColor(1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsBySize() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterBySize(1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsBySize(1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByColorAndSize() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterByColorAndSize(1L, 1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsByColorAndSize(1L, 1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByColorcat() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterByColorcat(1L, 1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsByColorcat(1L, 1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductById() {
        Optional<Product> expected = Optional.of(new Product());
        when(productRepository.findById(1L)).thenReturn(expected);
        Optional<Product> result = productService.getProductById(1L);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsBySizecat() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterBySizecat(1L, 1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsBySizecat(1L, 1L, pageable);
        assertEquals(expected, result);
    }

    @Test
    public void testGetProductsByColorAndSizecat() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.filterByColorAndSizecat(1L, 1L, 1L, pageable)).thenReturn(expected);
        Page<Product> result = productService.getProductsByColorAndSizecat(1L, 1L, 1L, pageable);
        assertEquals(expected, result);
    }
}
