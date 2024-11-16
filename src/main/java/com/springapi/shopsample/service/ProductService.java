package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;

/**
 * IProductService is an interface that extends the IBaseService interface
 * to provide CRUD operations specifically for ProductEntity and ProductDto.
 * It uses ProductEntity as the entity type, ProductDto as the DTO type,
 * and Long as the identifier type.
 */
public interface ProductService extends BaseService<ProductEntity, ProductDto, Long> {
}