package com.springapi.shopsample.entity.product;

import com.springapi.shopsample.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * ProductEntity is an entity class that represents a product in the system.
 * It extends the BaseEntity class to inherit common properties and methods.
 * This class is annotated with @Entity to indicate that it is a JPA entity.
 * The @Table annotation specifies the table name in the database.
 * The @Getter and @Setter annotations are used to generate getter and setter methods for the fields.
 */
@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

    /**
     * The name of the product.
     * This field is non-nullable.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the product.
     * This field has a maximum length of 510 characters.
     */
    @Column(name = "description", length = 510)
    private String description;

    /**
     * The price of the product.
     * This field is non-nullable and has a precision of 18 and a scale of 2.
     */
    @Column(name = "price", precision = 18, scale = 2, nullable = false)
    private BigDecimal price;

    /**
     * The URL of the product image.
     * This field is non-nullable.
     */
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

}