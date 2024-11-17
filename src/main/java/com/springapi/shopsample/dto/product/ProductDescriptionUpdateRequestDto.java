package com.springapi.shopsample.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * ProductDescriptionUpdateRequestDto is a data transfer object used for updating
 * the description of a product.
 */
@Getter
@Setter
public class ProductDescriptionUpdateRequestDto {

    /**
     * The description of the product.
     */
    @JsonProperty
    private String description;

}