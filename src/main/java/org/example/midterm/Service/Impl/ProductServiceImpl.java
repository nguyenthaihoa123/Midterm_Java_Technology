package org.example.midterm.Service.Impl;

import org.example.midterm.Service.ProductService;
import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.repository.CartItemRepository;
import org.example.midterm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product addProduct(Product product) {
        try{
            productRepository.save(product);
            return product;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product updateProduct(Product product) {
        try {
            // Kiểm tra xem sản phẩm đã tồn tại trong cơ sở dữ liệu chưa
            if (productRepository.existsById(product.getId())) {
                // Nếu sản phẩm tồn tại, tiến hành cập nhật thông tin sản phẩm
                cleanUpCartItems(product);
                Product updatedProduct = productRepository.save(product);
                return updatedProduct;
            } else {
                // Nếu sản phẩm không tồn tại, trả về null
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private void cleanUpCartItems(Product product) {
        // Lấy danh sách các CartItem của sản phẩm
        List<CartItem> cartItems = product.getCartItems();
        if (cartItems != null && !cartItems.isEmpty()) {
            // Lặp qua từng CartItem để kiểm tra và xóa
            for (CartItem cartItem : cartItems) {
                if (cartItem.getProduct() == null || !cartItem.getProduct().equals(product)) {
                    // Nếu CartItem không tham chiếu đến sản phẩm hoặc tham chiếu đến sản phẩm khác, xóa nó
                    cartItemRepository.delete(cartItem);
                }
            }
        }
    }


}
