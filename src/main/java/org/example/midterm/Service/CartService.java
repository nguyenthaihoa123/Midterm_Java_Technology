package org.example.midterm.Service;

import org.example.midterm.DTO.ProductCartDTO;
import org.example.midterm.model.Cart;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;

import java.util.List;

public interface CartService {
    ProductCartDTO addProduct(Long id,Product product);
    ProductCartDTO changeProductQuantity(Long id,Product product,int quantity);

    void minusCartItem(Long cartItemId);
    int getQuantity(Long id);

    List<ProductCartDTO> getAllProduct(Long id);

    int getTotal(Long id);

    CartItem findCartItemByUserIdAndProductId(Long userId, Long productId);

    void removeCartItem(CartItem cartItem);

    void removeAllCartItem(Cart cart);
}
