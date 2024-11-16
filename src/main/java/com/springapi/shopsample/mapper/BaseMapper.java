package com.springapi.shopsample.mapper;

import org.mapstruct.MappingTarget;

/**
 * BaseMapper is an interface for mapping between entities and DTOs.
 *
 * @param <E> the type of the entity
 * @param <D> the type of the DTO
 */
public interface BaseMapper<E, D> {

    /**
     * Maps an entity to a DTO.
     *
     * @param entity the entity to map
     * @return the corresponding DTO
     */
    D toDto(E entity);

    /**
     * Maps a DTO to an entity.
     *
     * @param dto the DTO to map
     * @return the corresponding entity
     */
    E toEntity(D dto);

    /**
     * Updates an existing entity from the given DTO.
     *
     * @param dto the DTO to update the entity from
     * @param entity the entity to be updated
     */
    void updateEntityFromDto(D dto, @MappingTarget E entity);
}