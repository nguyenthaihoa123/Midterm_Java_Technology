package org.example.midterm.Service.Impl;

import org.example.midterm.Service.BrandService;
import org.example.midterm.model.Brand;
import org.example.midterm.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            return brand;
        } else {
            return null;
        }
    }



    @Override
    public ResponseEntity<Brand> addBrand(Brand newBrand) {
        try {
            Brand savedBrand = brandRepository.save(newBrand);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBrand);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override
    public Brand updateBrand(Brand updatedBrand) {
        if (updatedBrand != null) {
            Brand savedBrand = brandRepository.save(updatedBrand);
            return savedBrand;
        } else {
            return null;
        }
    }
    @Override
    public Brand findByID(Long id) {
        return brandRepository.findById(id).get();
    }
}
