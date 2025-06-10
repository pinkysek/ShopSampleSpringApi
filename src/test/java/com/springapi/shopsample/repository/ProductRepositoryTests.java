package com.springapi.shopsample.repository;

import com.springapi.shopsample.entity.product.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ProductRepositoryTests is a test class for the ProductRepository.
 * It uses Spring Boot's testing support and runs with the "test" profile.
 * The class also executes SQL scripts to set up the test data.
 */
@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = "/test-products.sql")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Tests that all products are retrieved successfully.
     * Verifies that the first product in the list has the expected name.
     */
    @Test
    void testFindAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        assertEquals("Laptop", products.getFirst().getName());
    }

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