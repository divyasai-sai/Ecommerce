package com.example.model.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.ProductVariant;
import com.example.model.Size;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class SizeTest {

    @Mock
    private ProductVariant productVariant;

    private Validator validator;

    @BeforeEach
  void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
     void testSizeFields() {
        Size size = new Size();
        size.setId(1L);
        size.setSize("Medium");

        List<ProductVariant> productVariants = new ArrayList<>();
        productVariants.add(productVariant);
        size.setProductVariants(productVariants);

        assertEquals(1L, size.getId());
        assertEquals("Medium", size.getSize());
        assertNotNull(size.getProductVariants());
        assertEquals(1, size.getProductVariants().size());
        assertTrue(size.getProductVariants().contains(productVariant));
    }

   
}
