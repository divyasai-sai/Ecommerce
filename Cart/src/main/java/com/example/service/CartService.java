//package com.example.service;
// 
//import java.math.BigDecimal;
//
//import java.util.List;
//import java.util.stream.Collectors;
// 
//import org.springframework.stereotype.Service;
// 
//import com.example.dto.ProductDetailsDTO;
//import com.example.model.Cart;
//import com.example.model.CartItem;
//import com.example.model.ProductVariant;
//import com.example.repository.CartItemRepository;
//import com.example.repository.CartRepository;
//import com.example.repository.ProductVariantRepository;
//import com.example.repository.UserRepository;
// 
//@Service
//public class CartService {
// 
//    private final CartRepository cartRepository;
//    private final CartItemRepository cartItemRepository;
//    private final ProductVariantRepository productVariantRepository;
//    private final UserRepository userRepository; 
// 
//    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
//                       ProductVariantRepository productVariantRepository, UserRepository userRepository) {
//        this.cartRepository = cartRepository; 
//        this.cartItemRepository = cartItemRepository; 
//        this.productVariantRepository = productVariantRepository; 
//        this.userRepository = userRepository;
//    }
//  
//    public List<ProductDetailsDTO> getCartByUserId(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart == null) {
//            return null;
//        }
//        return cart.getItems().stream().map(item -> {
//            ProductDetailsDTO dto = new ProductDetailsDTO();
//            dto.setProductId(item.getProductVariant().getProduct().getId());
//            dto.setProductName(item.getProductVariant().getProduct().getName());
//            dto.setProductDescription(item.getProductVariant().getProduct().getDescription());
//            dto.setVariantId(item.getProductVariant().getId());
//            dto.setSize(item.getProductVariant().getSize().getSize());
//            dto.setColor(item.getProductVariant().getColor().getColor());
//            dto.setStock(item.getProductVariant().getStock());
//            dto.setPrice(item.getProductVariant().getPrice());
//            dto.setQuantity(item.getQuantity());
//            return dto;
//        }).collect(Collectors.toList());
//    }
// 
//    public Long findByProdIdColorIdSizeId(Long prodId, Long colorId, Long sizeId) {
//        return productVariantRepository.findByProdIdColorIdSizeId(prodId, colorId, sizeId);
//    }
// 
//    public void addToCart(Long userId, ProductVariant productVariant, int quantity) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart == null) {
//            cart = new Cart();
//            cart.setUser(userRepository.findById(userId).get());
//            cart = cartRepository.save(cart);
//        }
//        CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariant.getId());
//        if (cartItem == null) {
//            cartItem = new CartItem();
//            cartItem.setCart(cart);
//            cartItem.setProductVariant(productVariant);
//            cartItem.setQuantity(quantity);
//            cartItemRepository.save(cartItem);
//        } else {
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//            cartItemRepository.save(cartItem);
//        }
//    }
// 
//    public void removeFromCart(Long userId, Long productVariantId) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart != null) {
//            List<CartItem> items = cart.getItems();
//            items.removeIf(item -> item.getProductVariant().getId().equals(productVariantId));
//            cartRepository.save(cart);
//        }
//    }
// 
//    public void updateQuantity(Long userId, Long productVariantId, int quantity) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart != null) {
//            CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariantId);
//            if (cartItem != null) {
//                cartItem.setQuantity(quantity);
//                cartItemRepository.save(cartItem);
//            }
//        }
//    }
// 
//    public BigDecimal getCartSummary(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart != null) {
//            return cart.getItems().stream()
//                    .map(item -> item.getProductVariant().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
//                    .reduce(BigDecimal.ZERO, BigDecimal::add);
//        }
//        return BigDecimal.ZERO;
//    }
// 
//    public boolean checkStock(Long userId) {
//        Cart cart = cartRepository.findByUserId(userId);
//        if (cart == null) {
//            return false;
//        }
//        for (CartItem item : cart.getItems()) {
//            if (item.getQuantity() > item.getProductVariant().getStock()) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
// 

package com.example.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.ProductDetailsDTO;
import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.ProductVariant;
import com.example.repository.CartItemRepository;
import com.example.repository.CartRepository;
import com.example.repository.ProductVariantRepository;
import com.example.repository.UserRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantRepository productVariantRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       ProductVariantRepository productVariantRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productVariantRepository = productVariantRepository;
        this.userRepository = userRepository;
    }

    public List<ProductDetailsDTO> getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return null;
        }
        return cart.getItems().stream().map(item -> {
            ProductDetailsDTO dto = new ProductDetailsDTO();
            dto.setProductId(item.getProductVariant().getProduct().getId());
            dto.setProductName(item.getProductVariant().getProduct().getName());
            dto.setProductDescription(item.getProductVariant().getProduct().getDescription());
            dto.setVariantId(item.getProductVariant().getId());
            dto.setSize(item.getProductVariant().getSize().getSize());
            dto.setColor(item.getProductVariant().getColor().getColor());
            dto.setStock(item.getProductVariant().getStock());
            dto.setPrice(item.getProductVariant().getPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    public Long findByProdIdColorIdSizeId(Long prodId, Long colorId, Long sizeId) {
        return productVariantRepository.findByProdIdColorIdSizeId(prodId, colorId, sizeId);
    }

    public void addToCart(Long userId, ProductVariant productVariant, int quantity) throws Exception {
        if (productVariant.getStock() < quantity) {
            throw new Exception("Insufficient stock for product variant: " + productVariant.getId());
        }
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId).get());
            cart = cartRepository.save(cart);
        }
        CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariant.getId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProductVariant(productVariant);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        } else {
            int newQuantity = cartItem.getQuantity() + quantity;
            if (productVariant.getStock() < newQuantity) {
                throw new Exception("Insufficient stock for product variant: " + productVariant.getId());
            }
            cartItem.setQuantity(newQuantity);
            cartItemRepository.save(cartItem);
        }
    }

    public void removeFromCart(Long userId, Long productVariantId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            List<CartItem> items = cart.getItems();
            items.removeIf(item -> item.getProductVariant().getId().equals(productVariantId));
            cartRepository.save(cart);
        }
    }

    public void updateQuantity(Long userId, Long productVariantId, int quantity) throws Exception {
        ProductVariant productVariant = productVariantRepository.findById(productVariantId)
                .orElseThrow(() -> new Exception("Product variant not found"));
        if (productVariant.getStock() < quantity) {
            throw new Exception("Insufficient stock for product variant: " + productVariantId);
        }
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), productVariantId);
            if (cartItem != null) {
                cartItem.setQuantity(quantity);
                cartItemRepository.save(cartItem);
            }
        }
    }

    public BigDecimal getCartSummary(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            return cart.getItems().stream()
                    .map(item -> item.getProductVariant().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }

    public boolean checkStock(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            return false;
        }
        for (CartItem item : cart.getItems()) {
            if (item.getQuantity() > item.getProductVariant().getStock()) {
                return false;
            }
        }
        return true;
    }
}
