package com.springapi.shopsample.controller;

import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.dto.product.ProductDescriptionUpdateRequestDto;
import com.springapi.shopsample.exception.ResourceConflictException;
import com.springapi.shopsample.exception.ResourceNotFoundException;
import com.springapi.shopsample.service.ProductService;
import com.springapi.shopsample.dto.ApiErrorDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products", description = "API for managing products.")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Get a product by ID", description = "Returns a product based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The product was successfully retrieved.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - The product with the specified ID was not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred while processing the request.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Optional<ProductDto> product = productService.getById(id);
        return product.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("The product with ID: " + id + " was not found."));
    }

    @Operation(summary = "Get all products", description = "Returns a list of all products.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The products were successfully retrieved.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred while processing the request.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get all products with paging support", description = "Returns a list of all products with paging support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The products were successfully retrieved.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PagingDto.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - The page size or page number must be greater than 0.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred while processing the request.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @GetMapping(value = "/paging", produces = "application/json")
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

    @Operation(summary = "Update a product (description only)", description = "Updates and returns the updates product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - The product description was successfully updated.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Not Found - The product with the specified ID was not found.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error - An error occurred while processing the request.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorDto.class))),
    })
    @PatchMapping(value = "/{id}/description", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ProductDto> updateProductDescription(@PathVariable Long id, @RequestBody ProductDescriptionUpdateRequestDto dto) {
        Optional<ProductDto> product = productService.updateDescription(id, dto);
        return product.map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("The product with ID: " + id + " was not found."));
    }
}
