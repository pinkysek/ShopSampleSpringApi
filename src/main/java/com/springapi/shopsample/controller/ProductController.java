package com.springapi.shopsample.controller;

import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.dto.product.ProductDescriptionUpdateRequestDto;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get a product by ID.
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Optional<ProductDto> product = productService.getById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all products.
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    // Get all products with paging.
    @GetMapping("/paging")
    public ResponseEntity<PagingDto<ProductDto>> getAllProductsWithPaging(
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (pageNumber < 1)
            throw new ResourceConflictException("Page number must be greater than 0.");

        if (pageSize < 1)
            throw new ResourceConflictException("Page size must be greater than 0.");

        PagingDto<ProductDto> products = productService.findAllWithPaging(pageNumber, pageSize);
        return ResponseEntity.ok(products);
    }

    // Update product description.
    @PatchMapping("/{id}/description")
    public ResponseEntity<ProductDto> updateProductDescription(@PathVariable Long id, @RequestBody ProductDescriptionUpdateRequestDto dto) {
        Optional<ProductDto> product = productService.updateDescription(id, dto);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
