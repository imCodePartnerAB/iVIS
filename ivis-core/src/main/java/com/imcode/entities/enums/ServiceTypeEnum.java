package com.imcode.entities.enums;

/**
 * Created by vitaly on 13.05.15.
 */
public enum ServiceTypeEnum {
    ELEMENTARY_SCHOOL("Elementary school"),
    SECONDARY_SCHOOL("Secondary school"),
    SPECIAL_SCHOOL("Special school"),
    AFTER_SCHOOL_CENTER("After school center");

    private String representation;

    ServiceTypeEnum(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
