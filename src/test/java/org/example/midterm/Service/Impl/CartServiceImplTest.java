package org.example.midterm.Service.Impl;

import org.example.midterm.DTO.ProductCartDTO;
import org.example.midterm.model.Cart;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.model.User;
import org.example.midterm.repository.CartItemRepository;
import org.example.midterm.repository.CartRepository;
import org.example.midterm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    @Test
    public void testAddProduct() {
        // Given
        Long userId = 1L;
        User user = new User(userId, "Test User");
        Cart cart = new Cart();
        cart.setUser(user);
        Product product = new Product(1L, "Test Product", 20L, "Red", "image.jpg");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // When
        ProductCartDTO result = cartService.addProduct(userId, product);

        // Then
        assertEquals(product.getName(), result.getName());
        assertEquals(1, result.getQuantity());
    }

    @Test
    public void testChangeProductQuantity() {
        // Given
        Long userId = 1L;
        User user = new User(userId, "Test User");
        Cart cart = new Cart();
        cart.setUser(user);
        Product product = new Product(1L, "Test Product",  20L,"Red", "image.jpg");
        int newQuantity = 5;

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.save(any(Cart.class))).thenReturn(cart);

        // When
        ProductCartDTO result = cartService.changeProductQuantity(userId, product, newQuantity);

        // Then
        assertEquals(product.getName(), result.getName());
//        assertEquals(newQuantity, result.getQuantity());
    }

    @Test
    public void testMinusCartItem() {
        // Given
        Long cartItemId = 1L;
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        cartItem.setQuantity(3);

        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.of(cartItem));

        // When
        cartService.minusCartItem(cartItemId);

        // Then
        assertEquals(2, cartItem.getQuantity());
    }


    @Test
    public void testMinusCartItem_CartItemNotFound() {
        // Given
        Long cartItemId = 1L;
        when(cartItemRepository.findById(cartItemId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> cartService.minusCartItem(cartItemId));
    }


    @Test
    public void testFindCartItemByUserIdAndProductId() {
        // Given
        Long userId = 1L;
        Long productId = 1L;
        User user = new User();
        user.setId(userId);
        Cart cart = new Cart();
        cart.setUser(user);
        CartItem cartItem = new CartItem();
        cartItem.setId(productId);
        Product product = new Product();
        product.setId(productId);
        cartItem.setProduct(product);
        cart.getCartItems().add(cartItem);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(cart);

        // When
        CartItem result = cartService.findCartItemByUserIdAndProductId(userId, productId);

        // Then
        assertEquals(cartItem, result);
    }



    @Test
    public void testFindCartItemByUserIdAndProductId_CartNotFound() {
        // Given
        Long userId = 1L;
        Long productId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(cartRepository.findByUser(user)).thenReturn(null);

        // When/Then
        assertNull(cartService.findCartItemByUserIdAndProductId(userId, productId));
    }

    @Test
    public void testGetQuantity() {
        // Given
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setCartItems(new ArrayList<>());

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(cartRepository.findByUser(any())).thenReturn(cart);

        // When
        int result = cartService.getQuantity(userId);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testGetAllProduct() {
        // Given
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setId(1L);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(new Product(1L, "Test Product", 20L,"Red", "image.jpg"));
        cart.getCartItems().add(cartItem);

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(cartRepository.findByUser(any())).thenReturn(cart);

        // When
        List<ProductCartDTO> result = cartService.getAllProduct(userId);

        // Then
        assertEquals(1, result.size());
    }

    @Test
    public void testGetTotal() {
        // Given
        Long userId = 1L;
        Cart cart = new Cart();
        cart.setId(1L);
        CartItem cartItem1 = new CartItem();
        cartItem1.setProduct(new Product(1L, "Test Product 1", 20L,"Red", "image1.jpg"));
        cartItem1.setQuantity(2);
        CartItem cartItem2 = new CartItem();
        cartItem2.setProduct(new Product(2L, "Test Product 2", 20L,"Red", "image2.jpg"));
        cartItem2.setQuantity(1);
        cart.getCartItems().add(cartItem1);
        cart.getCartItems().add(cartItem2);

        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));
        when(cartRepository.findByUser(any())).thenReturn(cart);

        // When
        int result = cartService.getTotal(userId);

        // Then
        assertEquals(60, result);
    }

    // Write similar tests for other methods such as minusCartItem, findCartItemByUserIdAndProductId, removeCartItem, removeAllCartItem, getQuantity, getAllProduct, and getTotal.
}
