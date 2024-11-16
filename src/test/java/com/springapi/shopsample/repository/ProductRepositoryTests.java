package com.springapi.shopsample.repository;

import com.springapi.shopsample.entity.product.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the ProductRepository.
 * Uses Spring Boot's test framework and MockMvc for testing.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProductRepositoryTests {

    @Autowired
    private IProductRepository productRepository;

    /**
     * Tests that a ProductEntity is saved successfully.
     */
    @Test
    void saveProductEntity_savesSuccessfully() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setImageUrl("http://example.com/image.jpg");

        ProductEntity savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
    }

    /**
     * Tests that a ProductEntity is returned when found by ID.
     */
    @Test
    void findById_returnsProductEntity() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setImageUrl("http://example.com/image.jpg");

        ProductEntity savedProduct = productRepository.save(product);
        Optional<ProductEntity> foundProduct = productRepository.findById(savedProduct.getId());

        assertTrue(foundProduct.isPresent());
        assertEquals("Test Product", foundProduct.get().getName());
    }

    /**
     * Tests that a ProductEntity is deleted successfully by ID.
     */
    @Test
    void deleteById_deletesProductEntity() {
        ProductEntity product = new ProductEntity();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setImageUrl("http://example.com/image.jpg");

        ProductEntity savedProduct = productRepository.save(product);
        productRepository.deleteById(savedProduct.getId());
        Optional<ProductEntity> foundProduct = productRepository.findById(savedProduct.getId());

        assertFalse(foundProduct.isPresent());
    }

    /**
     * Tests that saving a ProductEntity with a null name throws a DataIntegrityViolationException.
     */
    @Test
    void saveProductEntity_withNullName_throwsException() {
        ProductEntity product = new ProductEntity();
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(19.99));
        product.setImageUrl("http://example.com/image.jpg");

        assertThrows(DataIntegrityViolationException.class, () -> productRepository.save(product));
    }
}