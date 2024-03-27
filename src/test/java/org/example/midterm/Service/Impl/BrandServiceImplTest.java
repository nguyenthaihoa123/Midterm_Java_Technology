package org.example.midterm.Service.Impl;

import org.example.midterm.model.Brand;
import org.example.midterm.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Brand> brandList = new ArrayList<>();
        brandList.add(new Brand(1L, "Brand A"));
        brandList.add(new Brand(2L, "Brand B"));
        when(brandRepository.findAll()).thenReturn(brandList);

        // When
        List<Brand> result = brandService.getAll();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    public void testGetById_Exists() {
        // Given
        Long id = 1L;
        Brand brand = new Brand(id, "NIKE");
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));

        // When
        Brand result = brandService.getById(id);

        // Then
        assertEquals(brand, result);
    }

    @Test
    public void testGetById_NotExists() {
        // Given
        Long id = 4L;
        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Brand result = brandService.getById(id);

        // Then
        assertNull(result);
    }

    @Test
    public void testAddBrand_Success() {
        // Given
        Brand newBrand = new Brand(1L, "NIKE");
        when(brandRepository.save(newBrand)).thenReturn(newBrand);

        // When
        ResponseEntity<Brand> response = brandService.addBrand(newBrand);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newBrand, response.getBody());
    }

    @Test
    public void testAddBrand_Failure() {
        // Given
        Brand newBrand = new Brand(1L, "Brand A");
        when(brandRepository.save(newBrand)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Brand> response = brandService.addBrand(newBrand);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    public void testUpdateBrand() {
        // Given
        Brand updatedBrand = new Brand(1L, "Updated Brand");
        when(brandRepository.save(updatedBrand)).thenReturn(updatedBrand);

        // When
        Brand result = brandService.updateBrand(updatedBrand);

        // Then
        assertEquals(updatedBrand, result);
    }

    @Test
    public void testFindByID_Exists() {
        // Given
        Long id = 1L;
        Brand brand = new Brand(id, "Brand A");
        when(brandRepository.findById(id)).thenReturn(Optional.of(brand));

        // When
        Brand result = brandService.findByID(id);

        // Then
        assertEquals(brand, result);
    }



    // Write similar tests for other methods such as updateBrand, findByID, etc.
}

