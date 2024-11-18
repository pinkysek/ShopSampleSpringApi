package com.springapi.shopsample.service.impl;

import com.springapi.shopsample.dto.product.ProductDescriptionUpdateRequestDto;
import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;
import com.springapi.shopsample.mapper.ProductMapper;
import com.springapi.shopsample.repository.ProductRepository;
import com.springapi.shopsample.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ProductServiceImpl is a service class that provides CRUD operations for ProductEntity.
 * It extends the BaseServiceImpl class and implements the IProductService interface.
 * This class is annotated with @Service to indicate that it is a Spring service component.
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductDto, Long> implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Constructs a new ProductServiceImpl with the given IProductRepository and IProductMapper.
     *
     * @param productRepository the IProductRepository to use for CRUD operations
     * @param productMapper     the IProductMapper to use for entity-DTO mapping
     */
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Optional<ProductDto> updateDescription(Long id, ProductDescriptionUpdateRequestDto dto) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        if (productEntity == null) {
            logger.warn("Product with ID {} not found", id);
            return Optional.empty();
        } else {
            productEntity.setDescription(dto.getDescription());
            ProductEntity updatedProduct = productRepository.save(productEntity);
            logger.info("Updated description for product with ID {}", id);
            return Optional.of(productMapper.toDto(updatedProduct));
        }
    }
}