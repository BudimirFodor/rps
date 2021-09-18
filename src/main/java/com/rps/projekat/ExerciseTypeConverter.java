package com.rps.projekat;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ExerciseTypeConverter implements AttributeConverter<ExerciseType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ExerciseType excerciseType)  {
        return (excerciseType != null) ? excerciseType.getCode() : null;
    }

    @Override
    public ExerciseType convertToEntityAttribute(Integer code) {
        return (code != null) ? ExerciseType.get(code) : null;
    }
}
