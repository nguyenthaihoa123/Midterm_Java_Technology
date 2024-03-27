package org.example.midterm.Service.Impl;


import org.example.midterm.model.CartItem;
import org.example.midterm.model.Product;
import org.example.midterm.repository.CartItemRepository;
import org.example.midterm.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Tạo danh sách sản phẩm giả định
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 100));
        products.add(new Product(2L, "Product 2", 200));

        // Khi gọi productRepository.findAll(), trả về danh sách sản phẩm giả định
        when(productRepository.findAll()).thenReturn(products);

        // Gọi phương thức getAll() của productService
        List<Product> retrievedProducts = productService.getAll();

        // Kiểm tra xem danh sách sản phẩm trả về có phải là danh sách giả định không
        assert(retrievedProducts.size() == 2);
        assert(retrievedProducts.get(0).getName().equals("Product 1"));
        assert(retrievedProducts.get(1).getName().equals("Product 2"));
    }


    @Test
    void testSearchProductByName() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 100));
        products.add(new Product(2L, "Product 2", 200));

        when(productRepository.findByNameContainingIgnoreCase("Product")).thenReturn(products);

        List<Product> retrievedProducts = productService.searchByName("Product");

        assert(retrievedProducts.size() == 2);
        assert(retrievedProducts.get(0).getName().equals("Product 1"));
        assert(retrievedProducts.get(1).getName().equals("Product 2"));
    }

    @Test
    void testFindProductById() {
        Product product = new Product(1L, "Product 1", 100);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product retrievedProduct = productService.findById(1L);

        assert(retrievedProduct != null);
        assert(retrievedProduct.getName().equals("Product 1"));
    }

    @Test
    void testAddProduct() {
        Product productToAdd = new Product(1L, "Product 1", 100);
        when(productRepository.save(any(Product.class))).thenReturn(productToAdd);

        Product addedProduct = productService.addProduct(productToAdd);

        assert(addedProduct != null);
        assert(addedProduct.getName().equals("Product 1"));
    }

    @Test
    void testUpdateProduct() {
        Product productToUpdate = new Product(1L, "Updated Product", 150);
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(productToUpdate);

        Product updatedProduct = productService.updateProduct(productToUpdate);

        assert(updatedProduct != null);
        assert(updatedProduct.getName().equals("Updated Product"));
        assert(updatedProduct.getPrice() == 150);
    }

    @Test
    void testDeleteProduct() {
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
    // Viết các unit test cho các phương thức khác tương tự như trên
}
