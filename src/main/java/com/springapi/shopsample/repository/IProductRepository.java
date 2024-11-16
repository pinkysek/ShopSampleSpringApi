package com.springapi.shopsample.repository;

import com.springapi.shopsample.entity.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for ProductEntity.
 * Extends JpaRepository to provide CRUD operations for ProductEntity.
 */
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}
