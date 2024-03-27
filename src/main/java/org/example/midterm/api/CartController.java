package org.example.midterm.api;

import org.example.midterm.DTO.ProductCartDTO;
import org.example.midterm.Service.CartService;
import org.example.midterm.Service.ProductService;
import org.example.midterm.Service.UserService;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @PostMapping("/add-product/{productId}/{userId}")
    public ResponseEntity<ProductCartDTO> addProductToCart(@PathVariable Long productId,
                                                           @PathVariable Long userId) {
        User user = userService.getById(userId);
        Product product = productService.findById(productId);
        if (user != null && product != null) {
            ProductCartDTO productCartDTO = cartService.addProduct(user.getId(), product);
            if (productCartDTO != null) {
                return ResponseEntity.ok(productCartDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/change-quantity/{productId}/{userId}/{quantity}")
    public ResponseEntity<ProductCartDTO> changeProductQuantity(@PathVariable Long productId,
                                                                @PathVariable Long userId,
                                                                @PathVariable int quantity) {
        User user = userService.getById(userId);
        Product product = productService.findById(productId);
        if (user != null && product != null) {
            ProductCartDTO productCartDTO = cartService.changeProductQuantity(user.getId(),product, quantity);
            if (productCartDTO != null) {
                return ResponseEntity.ok(productCartDTO);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/remove-product/{productId}/{userId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId,
                                                        @PathVariable Long userId) {
        // Tìm kiếm cartItem theo userId và productId
        CartItem cartItem = cartService.findCartItemByUserIdAndProductId(userId, productId);
        if (cartItem != null) {
            // Nếu cartItem tồn tại, gọi phương thức để xóa nó khỏi giỏ hàng
            cartService.removeCartItem(cartItem);
            // Trả về phản hồi OK với thông báo
            return ResponseEntity.ok("Product removed from cart successfully");
        } else {
            // Nếu không tìm thấy cartItem, trả về phản hồi lỗi 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

}
