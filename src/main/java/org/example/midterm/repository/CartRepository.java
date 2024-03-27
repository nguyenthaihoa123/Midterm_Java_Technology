package org.example.midterm.repository;

import org.example.midterm.model.Cart;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findByUser(User user);
}
