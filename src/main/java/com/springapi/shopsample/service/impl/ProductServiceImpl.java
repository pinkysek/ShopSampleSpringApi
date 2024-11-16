package com.springapi.shopsample.service.impl;

import com.springapi.shopsample.dto.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;
import com.springapi.shopsample.mapper.ProductMapper;
import com.springapi.shopsample.repository.ProductRepository;
import com.springapi.shopsample.service.ProductService;
import org.springframework.stereotype.Service;

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
     * @param productMapper the IProductMapper to use for entity-DTO mapping
     */
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        super(productRepository, productMapper);
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
}