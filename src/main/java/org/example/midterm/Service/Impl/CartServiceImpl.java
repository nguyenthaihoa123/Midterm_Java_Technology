package org.example.midterm.Service.Impl;

import org.example.midterm.DTO.ProductCartDTO;
import org.example.midterm.Service.CartService;
import org.example.midterm.model.Cart;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.model.User;
import org.example.midterm.repository.CartItemRepository;
import org.example.midterm.repository.CartRepository;
import org.example.midterm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    @Override
    public ProductCartDTO addProduct(Long userId, Product product) {
        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy giỏ hàng của người dùng
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                // Nếu sản phẩm đã tồn tại, cập nhật số lượng
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartRepository.save(cart); // Lưu thay đổi vào cơ sở dữ liệu
                return new ProductCartDTO(product.getId(), product.getName(), product.getColor(), product.getPrice(), cartItem.getQuantity(),product.getImage());
            }
        }

        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        cartItems.add(newCartItem);
        cart.setCartItems(cartItems);
        cartRepository.save(cart); // Lưu thay đổi vào cơ sở dữ liệu
        return new ProductCartDTO(product.getId(), product.getName(), product.getColor(), product.getPrice(), 1,product.getImage());
    }

    @Override
    public ProductCartDTO changeProductQuantity(Long id, Product product, int quantity) {
        // Lấy thông tin người dùng từ cơ sở dữ liệu
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        // Lấy giỏ hàng của người dùng
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
        }

        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().getId().equals(product.getId())) {
                // Nếu sản phẩm đã tồn tại, cập nhật số lượng
                cartItem.setQuantity(quantity);
                cartRepository.save(cart); // Lưu thay đổi vào cơ sở dữ liệu
                return new ProductCartDTO(product.getId(), product.getName(), product.getColor(), product.getPrice(), cartItem.getQuantity(),product.getImage());
            }
        }

        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        cartItems.add(newCartItem);
        cart.setCartItems(cartItems);
        cartRepository.save(cart); // Lưu thay đổi vào cơ sở dữ liệu
        return new ProductCartDTO(product.getId(), product.getName(), product.getColor(), product.getPrice(), 1,product.getImage());
    }

    @Override
    public void minusCartItem(Long cartItemId) {
        // Kiểm tra xem cartItemId có tồn tại trong cơ sở dữ liệu không
        Optional<CartItem> optionalCartItem = cartItemRepository.findById(cartItemId);
        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            // Lấy số lượng hiện tại của CartItem
            int currentQuantity = cartItem.getQuantity();
            // Giảm số lượng
            int newQuantity = currentQuantity - 1;
            // Đảm bảo số lượng không nhỏ hơn 0
            if (newQuantity > 0) {
                cartItem.setQuantity(newQuantity);
                // Lưu thay đổi vào cơ sở dữ liệu
                cartItemRepository.save(cartItem);
            } else {
                // Nếu số lượng sau khi giảm nhỏ hơn hoặc bằng 0, xóa cartItem khỏi cơ sở dữ liệu
                Cart cart = cartRepository.findById(cartItem.getCart().getId()).get();
                cart.getCartItems().removeIf(c -> Objects.equals(c.getId(), cartItem.getId()));
                cartRepository.save(cart);
                cartItemRepository.deleteById(cartItem.getId());
                // Bạn cũng có thể ném ra một ngoại lệ hoặc thông báo lỗi ở đây nếu cần thiết
            }
        } else {
            // Nếu cartItemId không tồn tại trong cơ sở dữ liệu, xử lý theo ý của bạn,
            // ví dụ: ném ra một ngoại lệ hoặc thông báo lỗi
            throw new RuntimeException("CartItem không tồn tại");
        }
    }


    public CartItem findCartItemByUserIdAndProductId(Long userId, Long productId) {
        // Tìm Cart của User dựa trên userId
        User user = userRepository.findById(userId).get();
        Optional<Cart> optionalCart = Optional.ofNullable(cartRepository.findByUser(user));

        // Kiểm tra xem Cart có tồn tại không
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            // Lấy danh sách CartItem trong Cart
            List<CartItem> cartItems = cart.getCartItems();

            // Lặp qua danh sách CartItem để tìm CartItem có productId mong muốn
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct().getId().equals(productId)) {
                    return cartItem;
                }
            }
        }

        // Trả về null nếu không tìm thấy CartItem nào có productId mong muốn
        return null;
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        try {
            // Kiểm tra xem cartItem có tồn tại trong cơ sở dữ liệu không
            CartItem existingCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);
            if (existingCartItem == null) {
                throw new RuntimeException("CartItem not found in database."); // Ném ngoại lệ RuntimeException
            }
            // Xóa cartItem khỏi giỏ hàng và lưu thay đổi vào cơ sở dữ liệu
            else {
                Cart cart = cartRepository.findById(existingCartItem.getCart().getId()).get();
                cart.getCartItems().removeIf(c -> Objects.equals(c.getId(), existingCartItem.getId()));
                cartRepository.save(cart);
                cartItemRepository.deleteById(existingCartItem.getId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void removeAllCartItem(Cart cart) {
        try {
            cart.getCartItems().clear();
            cartRepository.save(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public int getQuantity(Long id) {
        User user = userRepository.findById(id).get();
        Cart cart = cartRepository.findByUser(user);
        if (cart != null) {
            return cart.getCartItems().size();
        } else {
            return 0;
        }
    }

    @Override
    public List<ProductCartDTO> getAllProduct(Long id) {

        List<ProductCartDTO> productCartDTOS = new ArrayList<>();
        User user = userRepository.findById(id).get();
        Cart cart = cartRepository.findByUser(user);
        if(cart!=null){
            for (CartItem item: cart.getCartItems()){
                Product product = item.getProduct();
                productCartDTOS.add(new ProductCartDTO(product.getId(), product.getName(), product.getColor(), product.getPrice(), item.getQuantity(),product.getImage()));
            }
        }

        return productCartDTOS;
    }

    @Override
    public int getTotal(Long id) {
        User user = userRepository.findById(id).get();
        Cart cart = cartRepository.findByUser(user);
        int total = 0;
        if(cart != null){
            for (CartItem cartItem : cart.getCartItems()) {
                total += cartItem.getTotalPrice();
            }
        }
        return total;
    }
}
