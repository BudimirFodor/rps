package com.rps.projekat;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ExerciseType implements Serializable {

    DISTANCE (200),
    WEIGHT (201),
    TIME (202);

    private static final Map<Integer, ExerciseType> statusMap = new HashMap<>();

    static {
        for (ExerciseType et : EnumSet.allOf(ExerciseType.class)) {
            statusMap.put(et.getCode(), et);
        }
    }

    private final Integer code;

    private ExerciseType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static ExerciseType get(Integer code) {
        return statusMap.get(code);
    }
}
