package com.example.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.ProductDetailsDTO;
import com.example.dto.RemoveFromCartRequest;
import com.example.dto.ToCart;
import com.example.dto.UpdateQuantityRequest;
import com.example.model.ProductVariant;
import com.example.model.User;
import com.example.repository.ProductVariantRepository;
import com.example.repository.UserRepository;
import com.example.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    private final UserRepository userRepository; 
    private final CartService cartService;
    private final ProductVariantRepository productVariantRepository;

    public CartController(UserRepository userRepository, CartService cartService, ProductVariantRepository productVariantRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.productVariantRepository = productVariantRepository;
    }  
     
    @GetMapping("/userIdByEmail")
    public ResponseEntity<Long> getUserIdByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user.getId());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductDetailsDTO>> getCart(@PathVariable Long userId) {
        List<ProductDetailsDTO> cartDTO = cartService.getCartByUserId(userId);
        if (cartDTO == null) {   
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody ToCart toCart) {
        try {
            logger.info("Adding to cart: {}", toCart);
            if (toCart.getUserId() == null || toCart.getProductId() == null || toCart.getColorId() == null || toCart.getSizeId() == null) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Missing required fields in request");
            }
            Long id = cartService.findByProdIdColorIdSizeId(toCart.getProductId(), toCart.getColorId(), toCart.getSizeId());
            if (id == null) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Product variant not found");
            }
            ProductVariant productVariant = productVariantRepository.findById(id).orElse(null);
            if (productVariant == null) {
                return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body("Product variant not found");
            }
            cartService.addToCart(toCart.getUserId(), productVariant, toCart.getQuantity());
            return ResponseEntity.ok("Product added to cart successfully");
        } catch (Exception e) {
            logger.error("An error occurred while adding to cart", e);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An error occurred while adding to cart: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestBody RemoveFromCartRequest removeFromCartRequest) {
        try {
            cartService.removeFromCart(removeFromCartRequest.getUserId(), removeFromCartRequest.getProductVariantId());
            return ResponseEntity.ok("Product removed from cart successfully");
        } catch (Exception e) {
            logger.error("An error occurred while removing from cart", e);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An error occurred while removing from cart");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateQuantity(@RequestBody UpdateQuantityRequest updateQuantityRequest) {
        try {
            cartService.updateQuantity(updateQuantityRequest.getUserId(), updateQuantityRequest.getProductVariantId(), updateQuantityRequest.getQuantity());
            return ResponseEntity.ok("Quantity updated successfully");
        } catch (Exception e) {
            logger.error("An error occurred while updating quantity", e);
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("An error occurred while updating quantity: " + e.getMessage());
        }
    }

    @GetMapping("/summary/{userId}")
    public ResponseEntity<BigDecimal> getCartSummary(@PathVariable Long userId) {
        BigDecimal cartSummary = cartService.getCartSummary(userId);
        return ResponseEntity.ok(cartSummary);
    }

    @GetMapping("/checkStock/{userId}")
    public ResponseEntity<Boolean> checkStock(@PathVariable Long userId) {
        boolean isStockAvailable = cartService.checkStock(userId);
        return ResponseEntity.ok(isStockAvailable);
    }
}
