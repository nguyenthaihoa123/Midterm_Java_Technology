package org.example.midterm.Service;

import org.example.midterm.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    List<Product> searchByName(String name);
    Product findById(Long id);

    Product addProduct(Product product);
    Product updateProduct(Product product);

    void deleteProduct(Long id);
}
