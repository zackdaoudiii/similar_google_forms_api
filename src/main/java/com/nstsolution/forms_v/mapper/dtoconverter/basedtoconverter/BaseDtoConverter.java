package com.nstsolution.forms_v.mapper.dtoconverter.basedtoconverter;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public abstract class BaseDtoConverter<E,D> {

    public abstract D convertToDto(E entity);
    public abstract E convertToEntity(D dto) throws JsonProcessingException;
    public abstract List<D> convertListDtos(List<E> entity);
    public abstract List<E> convertListEntities(List<D> dto) throws JsonProcessingException;
}
