package org.example.midterm.api;

import org.example.midterm.Service.BrandService;
import org.example.midterm.model.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.getAll();
        return ResponseEntity.ok().body(brands);
    }

    @PostMapping
    public ResponseEntity<Brand> addBrand(@RequestBody Brand newBrand) {
        return brandService.addBrand(newBrand);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand updatedBrand) {
        Brand existingBrand = brandService.getById(id);

        if (existingBrand != null) {
            updatedBrand.setId(id); // Ensure that the ID matches the path variable ID
            return ResponseEntity.ok().body(brandService.updateBrand(updatedBrand));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
