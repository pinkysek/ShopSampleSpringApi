package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.product.ProductDto;
import com.springapi.shopsample.dto.product.ProductDescriptionUpdateRequestDto;
import com.springapi.shopsample.entity.product.ProductEntity;

import java.util.Optional;

/**
 * ProductService is an interface that extends the BaseService interface
 * to provide CRUD operations specifically for ProductEntity and ProductDto.
 * It uses ProductEntity as the entity type, ProductDto as the DTO type,
 * and Long as the identifier type.
 */
public interface ProductService extends BaseService<ProductEntity, ProductDto, Long> {

    /**
     * Updates the description of a product.
     *
     * @param id  the identifier of the product to update
     * @param dto the DTO containing the new description
     * @return an Optional containing the updated ProductDto, or an empty Optional if the update failed
     */
    Optional<ProductDto> updateDescription(Long id, ProductDescriptionUpdateRequestDto dto);

}