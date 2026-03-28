package com.springapi.shopsample.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springapi.shopsample.dto.IdentifiedDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Product name is required")
    @Size(min = 1, max = 255, message = "Product name must be between 1 and 255 characters")
    @Schema(description = "The name of the product.", example = "Product Name")
    @JsonProperty
    private String name;

    @Size(max = 510, message = "Description must be at most 510 characters")
    @Schema(description = "The description of the product.", example = "This is a product description.")
    @JsonProperty
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.00", message = "Price must be zero or positive")
    @Digits(integer = 16, fraction = 2, message = "Price must have at most 16 integer digits and 2 decimal places")
    @Schema(description = "The price of the product.", example = "200.00")
    @JsonProperty
    private BigDecimal price;

    @NotBlank(message = "Image URL is required")
    @Schema(description = "The URL of the product's image.", example = "https://www.example.com/image.jpg")
    @JsonProperty
    private String imageUrl;
}
