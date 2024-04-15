package com.ssm.systemmeetmanagement.utils.abstractConverter;

public abstract class AbstractConverter<E,D> {
    public abstract E fromDto(D dto);
    public abstract D fromEntity(E entity);
}
