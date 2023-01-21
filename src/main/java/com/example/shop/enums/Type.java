package com.example.shop.enums;

import java.util.HashMap;
import java.util.Map;

public enum Type {
    CD("CD"),

    VINYL("Vinyl");

    public final String type;

    private static final Map<String, Type> BY_TYPE = new HashMap<>();

    static {
        for (Type e: values()) {
            BY_TYPE.put(e.type, e);
        }
    }

    Type(String type) {
        this.type = type;
    }

    public static Type getByType(String type) {
        return  BY_TYPE.get(type);
    }
}
