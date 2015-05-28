package com.imcode.entities.enums;

/**
 * Created by vitaly on 13.05.15.
 */
public enum CommunicationTypeEnum {
    MOBILE("Mobile"),
    WORK("Work"),
    HOME("Home");

    private String representation;

    CommunicationTypeEnum(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
