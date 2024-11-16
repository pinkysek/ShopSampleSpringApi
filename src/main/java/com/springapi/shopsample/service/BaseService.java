package com.springapi.shopsample.service;

import com.springapi.shopsample.dto.IdentifiedDto;
import com.springapi.shopsample.entity.IdentifiedEntity;

import java.util.Optional;

/**
 * IBaseService is a generic interface that defines common CRUD operations
 * for entities that implement the IIdentifiedEntity interface.
 *
 * @param <E>  the type of the entity
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
     * @return an Iterable containing Optionals of all entities
     */
    Iterable<Optional<D>> findAll();
}