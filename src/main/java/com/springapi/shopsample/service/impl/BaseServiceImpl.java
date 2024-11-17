package com.springapi.shopsample.service.impl;

import com.springapi.shopsample.dto.IdentifiedDto;
import com.springapi.shopsample.dto.PagingDto;
import com.springapi.shopsample.entity.IdentifiedEntity;
import com.springapi.shopsample.mapper.BaseMapper;
import com.springapi.shopsample.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * BaseServiceImpl is an abstract class that provides basic CRUD operations.
 * It implements the IBaseService interface and uses a JpaRepository for data access.
 *
 * @param <E>  the type of the entity
 * @param <D>  the type of the DTO
 * @param <ID> the type of the identifier
 */
public abstract class BaseServiceImpl<E extends IdentifiedEntity<ID>, D extends IdentifiedDto<ID>, ID> implements BaseService<E, D, ID> {

    private final JpaRepository<E, ID> repository;
    private final BaseMapper<E, D> mapper;

    /**
     * Constructs a new BaseServiceImpl with the given repository and mapper.
     *
     * @param repository the JpaRepository to use for data access
     * @param mapper     the IBaseMapper to use for entity-DTO mapping
     */
    protected BaseServiceImpl(JpaRepository<E, ID> repository, BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Creates a new entity from the given DTO.
     *
     * @param dto the DTO to create the entity from
     * @return an Optional containing the created DTO, or an empty Optional if the DTO is null
     */
    @Override
    @Transactional
    public Optional<D> create(D dto) {
        if (dto == null) {
            return Optional.empty();
        }
        E entity = mapper.toEntity(dto);
        E savedEntity = repository.save(entity);
        return Optional.of(mapper.toDto(savedEntity));
    }

    /**
     * Retrieves an entity by its ID and converts it to a DTO.
     *
     * @param id the ID of the entity to retrieve
     * @return an Optional containing the DTO, or an empty Optional if the entity is not found
     */
    @Override
    public Optional<D> getById(ID id) {
        return repository.findById(id).map(mapper::toDto);
    }

    /**
     * Updates an existing entity from the given DTO.
     *
     * @param dto the DTO to update the entity from
     * @return an Optional containing the updated DTO, or an empty Optional if the DTO is null or the entity does not exist
     */
    @Override
    @Transactional
    public Optional<D> update(D dto) {
        if (dto == null || dto.getId() == null || !repository.existsById(dto.getId())) {
            return Optional.empty();
        }
        E entity = mapper.toEntity(dto);
        entity.setId(dto.getId()); // Ensure ID consistency.
        E updatedEntity = repository.save(entity);
        return Optional.of(mapper.toDto(updatedEntity));
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     * @throws RuntimeException if the entity does not exist
     */
    @Override
    @Transactional
    public void delete(ID id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Entity with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<D> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PagingDto<D> findAllWithPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<E> entities = repository.findAll(pageable);

        List<D> dtoList = entities.stream()
                .map(mapper::toDto)
                .toList();

        return new PagingDto<>(dtoList, (int) entities.getTotalElements(), page, size);
    }
}