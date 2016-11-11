package com.imcode.entities.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by ruslan on 11.11.16.
 */
public enum Scope {

    GET, POST, PUT, DELETE;

    public static String [] getValues() {
        Scope[] values = values();
        return Arrays.stream(values)
                .map(scope -> scope.toString().toLowerCase())
                .toArray(String[]::new);
    }

    public static String getValuesCommaSerapeted() {
        Scope[] values = values();
        return Arrays.stream(values)
                .map(scope -> scope.toString().toLowerCase())
                .collect(Collectors.joining(","));
    }
}
