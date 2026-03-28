package com.springapi.shopsample.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * ProductDescriptionUpdateRequestDto is a data transfer object used for updating
 * the description of a product.
 */
@Getter
@Setter
@Schema(description = "Data Transfer Object for Product Description Update Request.")
public class ProductDescriptionUpdateRequestDto {

    @Size(max = 510, message = "Description must be at most 510 characters")
    @Schema(description = "The description of the product.", example = "This is a product description.")
    @JsonProperty
    private String description;
}
