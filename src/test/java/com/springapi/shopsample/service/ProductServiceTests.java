package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;
import com.springapi.shopsample.mapper.ProductMapper;
import com.springapi.shopsample.repository.ProductRepository;
import com.springapi.shopsample.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * ProductServiceTest is a test class for ProductServiceImpl.
 * It uses Mockito to mock dependencies and JUnit 5 for testing.
 */
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    /**
     * Sets up the test environment before each test.
     * Initialize mocks and injects them into the productService.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful creation of a product.
     * Verifies that the created product is not null and has the expected name.
     */
    @Test
    void createProductSuccessfully() {
        ProductDto productDto = new ProductDto();
        productDto.setName("Test Product");
        productDto.setPrice(BigDecimal.valueOf(10.00));
        ProductEntity productEntity = new ProductEntity();
        when(productMapper.toEntity(any(ProductDto.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(productDto);

        Optional<ProductDto> createdProduct = productService.create(productDto);

        assertTrue(createdProduct.isPresent());
        assertEquals("Test Product", createdProduct.get().getName());
    }

    /**
     * Tests the creation of a product with a null DTO.
     * Verifies that the created product is not present.
     */
    @Test
    void createProductWithNullDto() {
        Optional<ProductDto> createdProduct = productService.create(null);

        assertFalse(createdProduct.isPresent());
    }

    /**
     * Tests the successful retrieval of a product by its ID.
     * Verifies that the found product is not null and has the expected ID.
     */
    @Test
    void getProductByIdSuccessfully() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(1L);
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(productDto);

        Optional<ProductDto> foundProduct = productService.getById(1L);

        assertTrue(foundProduct.isPresent());
        assertEquals(1L, foundProduct.get().getId());
    }

    /**
     * Tests the retrieval of a product by its ID when the product is not found.
     * Verifies that the found product is not present.
     */
    @Test
    void getProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<ProductDto> foundProduct = productService.getById(1L);

        assertFalse(foundProduct.isPresent());
    }

    /**
     * Tests the successful update of a product.
     * Verifies that the updated product is not null and has the expected name.
     */
    @Test
    void updateProductSuccessfully() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setName("Updated Product");
        ProductEntity productEntity = new ProductEntity();
        when(productRepository.existsById(1L)).thenReturn(true);
        when(productMapper.toEntity(any(ProductDto.class))).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(productMapper.toDto(any(ProductEntity.class))).thenReturn(productDto);

        Optional<ProductDto> updatedProduct = productService.update(productDto);

        assertTrue(updatedProduct.isPresent());
        assertEquals("Updated Product", updatedProduct.get().getName());
    }

    /**
     * Tests the update of a product with a null DTO.
     * Verifies that the updated product is not present.
     */
    @Test
    void updateProductWithNullDto() {
        Optional<ProductDto> updatedProduct = productService.update(null);

        assertFalse(updatedProduct.isPresent());
    }

    /**
     * Tests the successful deletion of a product by its ID.
     * Verifies that no exception is thrown and the product repository's deleteById method is called once.
     */
    @Test
    void deleteProductSuccessfully() {
        when(productRepository.existsById(1L)).thenReturn(true);
        doNothing().when(productRepository).deleteById(1L);

        assertDoesNotThrow(() -> productService.delete(1L));
        verify(productRepository, times(1)).deleteById(1L);
    }

    /**
     * Tests the deletion of a product by its ID when the product is not found.
     * Verifies that a RuntimeException is thrown with the expected message.
     */
    @Test
    void deleteProductNotFound() {
        when(productRepository.existsById(1L)).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> productService.delete(1L));
        assertEquals("Entity with ID 1 does not exist.", exception.getMessage());
    }

    /**
     * Tests the successful retrieval of all products.
     * Verifies that the retrieved products are not null and have the expected size.
     */
    @Test
    void findAllProductsSuccessfully() {
        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        ProductEntity product2 = new ProductEntity();
        product2.setId(2L);
        ProductDto productDto1 = new ProductDto();
        productDto1.setId(1L);
        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);
        when(productRepository.findAll()).thenReturn(List.of(product1, product2));
        when(productMapper.toDto(product1)).thenReturn(productDto1);
        when(productMapper.toDto(product2)).thenReturn(productDto2);

        List<ProductDto> products = productService.findAll();

        assertNotNull(products);
        assertEquals(2, products.size());
    }

    /**
     * Tests the successful retrieval of all products with pagination.
     * Verifies that the retrieved products are not null and have the expected size and pagination details.
     */
    @Test
    void findAllProductsWithPagingSuccessfully() {
        ProductEntity product1 = new ProductEntity();
        product1.setId(1L);
        ProductEntity product2 = new ProductEntity();
        product2.setId(2L);
        ProductDto productDto1 = new ProductDto();
        productDto1.setId(1L);
        ProductDto productDto2 = new ProductDto();
        productDto2.setId(2L);

        Page<ProductEntity> productPage = new PageImpl<>(List.of(product1, product2), PageRequest.of(0, 2), 2);
        when(productRepository.findAll(any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toDto(product1)).thenReturn(productDto1);
        when(productMapper.toDto(product2)).thenReturn(productDto2);

        PagingDto<ProductDto> result = productService.findAllWithPaging(1, 2);

        assertNotNull(result);
        assertEquals(2, result.getItems().size());
        assertEquals(1, result.getPageNumber());
        assertEquals(2, result.getPageSize());
        assertEquals(2, result.getTotalCount());
        assertEquals(1, result.getTotalPages());
        assertFalse(result.isHasPrevious());
        assertFalse(result.isHasNext());
    }

    /**
     * Tests the retrieval of all products with pagination when the page is empty.
     * Verifies that the retrieved products are empty and pagination details are correct.
     */
    @Test
    void findAllProductsWithPagingEmptyPage() {
        Page<ProductEntity> productPage = new PageImpl<>(Collections.emptyList(), PageRequest.of(0, 2), 0);
        when(productRepository.findAll(any(Pageable.class))).thenReturn(productPage);

        PagingDto<ProductDto> result = productService.findAllWithPaging(1, 2);

        assertNotNull(result);
        assertTrue(result.getItems().isEmpty());
        assertEquals(1, result.getPageNumber());
        assertEquals(2, result.getPageSize());
        assertEquals(0, result.getTotalCount());
        assertEquals(0, result.getTotalPages());
        assertFalse(result.isHasPrevious());
        assertFalse(result.isHasNext());
    }

    /**
     * Tests the retrieval of all products with pagination when the page number is invalid.
     * Verifies that the retrieved products are empty and pagination details are correct.
     */
    @Test
    void findAllProductsWithPagingInvalidPage() {
        when(productRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        RuntimeException exception = assertThrows(IllegalArgumentException.class, () -> productService.findAllWithPaging(-1, 2));
        assertEquals("Page index must not be less than zero", exception.getMessage());
    }
}