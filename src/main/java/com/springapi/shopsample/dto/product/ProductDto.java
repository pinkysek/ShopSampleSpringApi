package com.springapi.shopsample.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapi.shopsample.dto.IdentifiedDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * ProductDto is a Data Transfer Object (DTO) for transferring product data.
 * It implements the IIdentifiedDto interface with Long as the identifier type.
 */
@Getter
@Setter
@Schema(description = "Data Transfer Object for Product.")
public class ProductDto implements IdentifiedDto<Long> {

    @Schema(description = "The unique identifier of the product", example = "1")
    @JsonProperty
    private Long id;

    @Schema(description = "The name of the product.", example = "Product Name")
    @JsonProperty
    private String name;

    @Schema(description = "The description of the product.", example = "This is a product description.")
    @JsonProperty
    private String description;

    @Schema(description = "The price of the product.", example = "200.00")
    @JsonProperty
    private BigDecimal price;

    @Schema(description = "The URL of the product's image..", example = "https://www.example.com/image.jpg")
    @JsonProperty
    private String imageUrl;

}