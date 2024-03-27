package org.example.midterm.api;

import org.example.midterm.Service.ProductService;
import org.example.midterm.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product newProduct) {
        Product createdProduct = productService.addProduct(newProduct);
        return ResponseEntity.ok().body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        // Kiểm tra xem sản phẩm có tồn tại trong hệ thống không
        if (productService.findById(id) != null) {
            updatedProduct.setId(id); // Đảm bảo rằng ID của sản phẩm phù hợp với ID trong đường dẫn
            Product updated = productService.updateProduct(updatedProduct); // Cập nhật thông tin sản phẩm
            return ResponseEntity.ok().body(updated);
        } else {
            // Nếu không tìm thấy sản phẩm, trả về lỗi 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        // Kiểm tra xem sản phẩm có tồn tại trong hệ thống không
        Product product = productService.findById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Delete success");
        } else {
            // Nếu không tìm thấy sản phẩm, trả về lỗi 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}

