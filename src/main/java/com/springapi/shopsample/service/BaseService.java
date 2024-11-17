package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.IdentifiedDto;
import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.entity.IdentifiedEntity;

import java.util.List;
import java.util.Optional;

/**
 * BaseService is a generic interface that defines common CRUD operations
 * for entities that implement the IdentifiedEntity interface.
 *
 * @param <E>  the type of the entity
 * @param <D>  the type of the DTO
 * @param <ID> the type of the entity's identifier
 */
public interface BaseService<E extends IdentifiedEntity<ID>, D extends IdentifiedDto<ID>, ID> {

    /**
     * Creates a new entity.
     *
     * @param entity the entity to create
     * @return an Optional containing the created entity, or an empty Optional if creation failed
     */
    Optional<D> create(D entity);

    /**
     * Retrieves an entity by its identifier.
     *
     * @param id the identifier of the entity to retrieve
     * @return an Optional containing the retrieved entity, or an empty Optional if no entity was found
     */
    Optional<D> getById(ID id);

    /**
     * Updates an existing entity.
     *
     * @param entity the entity to update
     * @return an Optional containing the updated entity, or an empty Optional if update failed
     */
    Optional<D> update(D entity);

    /**
     * Deletes an entity by its identifier.
     *
     * @param id the identifier of the entity to delete
     */
    void delete(ID id);

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    List<D> findAll();

    /**
     * Retrieves all entities with pagination.
     *
     * @param page the page number to retrieve
     * @param size the number of entities per page
     * @return a PagingDto containing the entities for the specified page
     */
    PagingDto<D> findAllWithPaging(int page, int size);
}