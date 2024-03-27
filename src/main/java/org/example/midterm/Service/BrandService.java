package org.example.midterm.Service;

import org.example.midterm.model.Brand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {
    List<Brand> getAll();
    Brand getById(Long id);

    ResponseEntity<Brand> addBrand(Brand newBrand);

    Brand findByID(Long id);

    Brand updateBrand(Brand updatedBrand);
}
