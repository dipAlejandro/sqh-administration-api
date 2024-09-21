package com.shaolinquanhu.admin.api.service;

/**
 *
 * @author dahl
 * @param <E> Entity
 * @param <D> DTO
 * @param <R> ResponseDTO
 */
public interface IConverter<E, D, R> {

    /**
     *
     * @param dto
     * @return
     */
    public E toEntity(D dto);

    /**
     *
     * @param entity
     * @return
     */
    public R toResponseDTO(E entity);
}
