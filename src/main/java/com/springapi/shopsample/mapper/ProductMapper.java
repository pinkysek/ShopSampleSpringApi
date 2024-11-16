package com.springapi.shopsample.mapper;

import com.springapi.shopsample.dto.ProductDto;
import com.springapi.shopsample.entity.product.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductEntity, ProductDto> {
}