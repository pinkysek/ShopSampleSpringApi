package com.springapi.shopsample.mapper.impl;

import com.springapi.shopsample.dto.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;
import com.springapi.shopsample.mapper.ProductMapper;

/**
 * ProductMapperImpl is an implementation of the ProductMapper interface.
 * It provides methods to map between ProductEntity and ProductDto.
 */
public class ProductMapperImpl implements ProductMapper {

    /**
     * Converts a ProductEntity to a ProductDto.
     *
     * @param entity the ProductEntity to convert
     * @return the converted ProductDto, or null if the entity is null
     */
    @Override
    public ProductDto toDto(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        ProductDto dto = new ProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    /**
     * Converts a ProductDto to a ProductEntity.
     *
     * @param dto the ProductDto to convert
     * @return the converted ProductEntity, or null if the dto is null
     */
    @Override
    public ProductEntity toEntity(ProductDto dto) {
        if (dto == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    /**
     * Updates an existing ProductEntity from the given ProductDto.
     *
     * @param dto the ProductDto to update the entity from
     * @param entity the ProductEntity to be updated
     */
    @Override
    public void updateEntityFromDto(ProductDto dto, ProductEntity entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImageUrl(dto.getImageUrl());
    }
}