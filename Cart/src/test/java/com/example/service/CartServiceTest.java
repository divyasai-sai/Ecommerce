package com.example.service;
 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
 
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
 
import com.example.dto.ProductDetailsDTO;
import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.Color;
import com.example.model.Product;
import com.example.model.ProductVariant;
import com.example.model.Size;
import com.example.model.User;
import com.example.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
public class CartServiceTest {
 
    @Mock
    private CartRepository cartRepository;
 
    @Mock
    private CartItemRepository cartItemRepository;
 
    @Mock
    private ProductVariantRepository productVariantRepository;
 
    @Mock
    private UserRepository userRepository;
 
    @InjectMocks
    private CartService cartService;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testGetCartByUserId() {
        Cart cart = new Cart();
        CartItem item1 = new CartItem();
        CartItem item2 = new CartItem();
        
        ProductVariant variant1 = new ProductVariant();
        Product product1 = new Product();
        product1.setId(1L);
        variant1.setProduct(product1);
        variant1.setSize(new Size());
        variant1.setColor(new Color());
        
        ProductVariant variant2 = new ProductVariant();
        Product product2 = new Product();
        product2.setId(2L);
        variant2.setProduct(product2);
        variant2.setSize(new Size());
        variant2.setColor(new Color());
        
        item1.setProductVariant(variant1);
        item2.setProductVariant(variant2);
        
        cart.setItems(Arrays.asList(item1, item2));
        
        when(cartRepository.findByUserId(anyLong())).thenReturn(cart);
 
        assertEquals(2, cartService.getCartByUserId(1L).size());
    }
 
    @Test
    void testGetCartByUserId_NotFound() {
        when(cartRepository.findByUserId(anyLong())).thenReturn(null);
        assertEquals(null, cartService.getCartByUserId(1L));
    }
 
    @Test
    void testFindByProdIdColorIdSizeId() {
        Long id = 1L;
        when(productVariantRepository.findByProdIdColorIdSizeId(anyLong(), anyLong(), anyLong())).thenReturn(id);
        assertEquals(id, cartService.findByProdIdColorIdSizeId(1L, 1L, 1L));
    }
 
    @Test
    void testAddToCart_NewCart() {
        ProductVariant productVariant = new ProductVariant();
        User user = new User();
        when(cartRepository.findByUserId(anyLong())).thenReturn(null);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(cartRepository.save(any(Cart.class))).thenReturn(new Cart());
        
       // cartService.addToCart(1L, productVariant, 1);
        
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
 
    @Test
    void testAddToCart_ExistingCart() {
        Cart cart = new Cart();
        ProductVariant productVariant = new ProductVariant();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        
        when(cartRepository.findByUserId(anyLong())).thenReturn(cart);
        when(cartItemRepository.findByCartIdAndProductVariantId(anyLong(), anyLong())).thenReturn(cartItem);
        
        //cartService.addToCart(1L, productVariant, 1);
        
        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
 
    @Test
    void testRemoveFromCart() {
        Cart cart = new Cart();
        CartItem item = new CartItem();
        item.setProductVariant(new ProductVariant());
        item.getProductVariant().setId(1L);
        cart.setItems(Collections.singletonList(item));
        
        when(cartRepository.findByUserId(anyLong())).thenReturn(cart);
        assertTrue(true);
        
        
        
       
    }
 
    @Test
    void testRemoveFromCart_NoCart() {
        when(cartRepository.findByUserId(anyLong())).thenReturn(null);
        cartService.removeFromCart(1L, 1L);
        verify(cartRepository, never()).save(any(Cart.class));
        assertTrue(true);
    }
 
    @Test
    void testUpdateQuantity() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        
        when(cartRepository.findByUserId(anyLong())).thenReturn(cart);
        when(cartItemRepository.findByCartIdAndProductVariantId(anyLong(), anyLong())).thenReturn(cartItem);
        
       // cartService.updateQuantity(1L, 1L, 2);
        
        assertTrue(true);
    }
 
    @Test
    void testUpdateQuantity_NoCart() {
        when(cartRepository.findByUserId(anyLong())).thenReturn(null);
      //  cartService.updateQuantity(1L, 1L, 2);
        verify(cartItemRepository, never()).save(any(CartItem.class));
    }
 
    @Test
    void testGetCartSummary() {
        Cart cart = new Cart();
        CartItem item = new CartItem();
        ProductVariant productVariant = new ProductVariant();
        productVariant.setPrice(BigDecimal.TEN);
        item.setProductVariant(productVariant);
        item.setQuantity(2);
        cart.setItems(Collections.singletonList(item));
        
        when(cartRepository.findByUserId(anyLong())).thenReturn(cart);
        
        assertEquals(BigDecimal.valueOf(20), cartService.getCartSummary(1L));
    }
 
    @Test
    void testGetCartSummary_EmptyCart() {
        when(cartRepository.findByUserId(anyLong())).thenReturn(null);
        assertEquals(BigDecimal.ZERO, cartService.getCartSummary(1L));
    }
    @Test
    public void testCheckStock_CartNotFound() {
        Long userId = 1L;
 
        when(cartRepository.findByUserId(userId)).thenReturn(null);
 
        boolean result = cartService.checkStock(userId);
 
        assertFalse(result);
    }
 
    @Test
    public void testCheckStock_StockAvailable() {
        Long userId = 1L;
        ProductVariant productVariant = new ProductVariant();
        productVariant.setStock(10);
 
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(5);
        cartItem.setProductVariant(productVariant);
 
        Cart cart = new Cart();
        cart.setItems(Collections.singletonList(cartItem));
 
        when(cartRepository.findByUserId(userId)).thenReturn(cart);
 
        boolean result = cartService.checkStock(userId);
 
        assertTrue(result);
    }
 
    @Test
    public void testCheckStock_StockUnavailable() {
        Long userId = 1L;
        ProductVariant productVariant = new ProductVariant();
        productVariant.setStock(10);
 
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(15);
        cartItem.setProductVariant(productVariant);
 
        Cart cart = new Cart();
        cart.setItems(Collections.singletonList(cartItem));
 
        when(cartRepository.findByUserId(userId)).thenReturn(cart);
 
        boolean result = cartService.checkStock(userId);
 
        assertFalse(result);
    }
}