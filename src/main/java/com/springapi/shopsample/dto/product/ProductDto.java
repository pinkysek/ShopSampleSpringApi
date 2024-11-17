package com.springapi.shopsample.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapi.shopsample.dto.IdentifiedDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * ProductDto is a Data Transfer Object (DTO) for transferring product data.
 * It implements the IIdentifiedDto interface with Long as the identifier type.
 */
@Getter
@Setter
public class ProductDto implements IdentifiedDto<Long> {

    /**
     * The unique identifier of the product.
     */
    @JsonProperty
    private Long id;

    /**
     * The name of the product.
     */
    @JsonProperty
    private String name;

    /**
     * The description of the product.
     */
    @JsonProperty
    private String description;

    /**
     * The price of the product.
     */
    @JsonProperty
    private BigDecimal price;

    /**
     * The URL of the product's image.
     */
    @JsonProperty
    private String imageUrl;

}