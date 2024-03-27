package org.example.midterm.repository;

import org.example.midterm.model.Cart;
import org.example.midterm.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    void deleteAllByCart(Cart cart);
}
