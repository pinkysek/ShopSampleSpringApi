package com.springapi.shopsample.controller;

import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.dto.product.ProductDescriptionUpdateRequestDto;
import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.exception.ResourceNotFoundException;
import com.springapi.shopsample.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.anyLong;

/**
 * Unit tests for the ProductController class.
 * This class contains tests for various endpoints of the ProductController.
 */
class ProductControllerTests {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    /**
     * Sets up the test environment before each test.
     * Initialize mocks and injects them into the productController.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the successful retrieval of a product by its ID.
     * Verifies that the response status is OK and the product matches the expected product.
     */
    @Test
    void getProductByIdSuccessfully() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        when(productService.getById(1L)).thenReturn(Optional.of(productDto));

        ResponseEntity<ProductDto> response = productController.getProduct(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    /**
     * Tests the retrieval of a product by its ID when the product is not found.
     * Verifies that a ResourceNotFoundException is thrown with the expected message.
     */
    @Test
    void getProductByIdNotFound() {
        when(productService.getById(anyLong())).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productController.getProduct(1L));

        assertEquals("The product with ID: 1 was not found", exception.getMessage());
    }

    /**
     * Tests the successful retrieval of all products.
     * Verifies that the response status is OK and the number of products matches the expected size.
     */
    @Test
    void getAllProductsSuccessfully() {
        ProductDto productDto1 = new ProductDto();
        ProductDto productDto2 = new ProductDto();
        when(productService.findAll()).thenReturn(List.of(productDto1, productDto2));

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
    }

    /**
     * Tests the successful retrieval of all products with pagination.
     * Verifies that the response status is OK and the paging details match the expected values.
     */
    @Test
    void getAllProductsWithPagingSuccessfully() {
        PagingDto<ProductDto> pagingDto = new PagingDto<>(List.of(), 1, 1, 1);
        when(productService.findAllWithPaging(1, 10)).thenReturn(pagingDto);

        ResponseEntity<PagingDto<ProductDto>> response = productController.getAllProductsWithPaging(1, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagingDto, response.getBody());
    }

    /**
     * Tests the retrieval of all products with pagination when the page number is invalid.
     * Verifies that a ResourceConflictException is thrown with the expected message.
     */
    @Test
    void getAllProductsWithPagingInvalidPageNumber() {
        ResourceConflictException exception = assertThrows(ResourceConflictException.class, () -> productController.getAllProductsWithPaging(0, 10));

        assertEquals("Page number must be greater than 0", exception.getMessage());
    }

    /**
     * Tests the retrieval of all products with pagination when the page size is invalid.
     * Verifies that a ResourceConflictException is thrown with the expected message.
     */
    @Test
    void getAllProductsWithPagingInvalidPageSize() {
        ResourceConflictException exception = assertThrows(ResourceConflictException.class, () -> productController.getAllProductsWithPaging(1, 0));

        assertEquals("Page size must be greater than 0", exception.getMessage());
    }

    /**
     * Tests the successful update of a product's description.
     * Verifies that the response status is OK and the updated product matches the expected product.
     */
    @Test
    void updateProductDescriptionSuccessfully() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        ProductDescriptionUpdateRequestDto updateRequestDto = new ProductDescriptionUpdateRequestDto();
        when(productService.updateDescription(1L, updateRequestDto)).thenReturn(Optional.of(productDto));

        ResponseEntity<ProductDto> response = productController.updateProductDescription(1L, updateRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
    }

    /**
     * Tests the update of a product's description when the product is not found.
     * Verifies that a ResourceNotFoundException is thrown with the expected message.
     */
    @Test
    void updateProductDescriptionNotFound() {
        ProductDescriptionUpdateRequestDto updateRequestDto = new ProductDescriptionUpdateRequestDto();
        when(productService.updateDescription(anyLong(), any(ProductDescriptionUpdateRequestDto.class))).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> productController.updateProductDescription(1L, updateRequestDto));
        assertEquals("The product with ID: 1 was not found", exception.getMessage());
    }
}