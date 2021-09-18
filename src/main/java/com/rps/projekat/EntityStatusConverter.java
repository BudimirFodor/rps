package com.rps.projekat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EntityStatusConverter implements AttributeConverter<EntityStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EntityStatus entityStatus) {
        return (entityStatus != null) ? entityStatus.getCode() : null;
    }

    @Override
    public EntityStatus convertToEntityAttribute(Integer code) {
        return (code != null) ? EntityStatus.get(code) : null;
    }
}
