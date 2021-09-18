package com.rps.projekat;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum EntityStatus implements Serializable {
    ACTIVE (100),
    INACTIVE (101),
    COMPLETED (102),
    DELETED (103);

    private static final Map<Integer, EntityStatus> statusMap = new HashMap<>();

    static {
        for (EntityStatus es : EnumSet.allOf(EntityStatus.class)) {
            statusMap.put(es.getCode(), es);
        }
    }

    private final Integer code;

    private EntityStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EntityStatus get(Integer code) {
        return statusMap.get(code);
    }

    @Override
    public String toString() {
        return "EntityStatus{" +
                "code=" + code +
                '}';
    }
}
