package com.springapi.shopsample.dto;

/**
 * IdentifiedDto is an interface for DTOs that have an identifier.
 *
 * @param <ID> the type of the identifier
 */
public interface IdentifiedDto<ID> {

    /**
     * Gets the identifier of the DTO.
     *
     * @return the identifier of the DTO
     */
    ID getId();

    /**
     * Sets the identifier of the DTO.
     *
     * @param id the identifier to set
     */
    void setId(ID id);

}