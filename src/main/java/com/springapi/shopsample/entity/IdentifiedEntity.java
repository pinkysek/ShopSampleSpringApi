package com.springapi.shopsample.entity;

/**
 * Interface for entities with an identifier.
 *
 * @param <ID> the type of the identifier
 */
public interface IdentifiedEntity<ID> {

    /**
     * Gets the identifier of the entity.
     *
     * @return the identifier of the entity
     */
    ID getId();

    /**
     * Sets the identifier of the entity.
     *
     * @param id the identifier to set
     */
    void setId(ID id);

}