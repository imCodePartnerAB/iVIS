package com.imcode.entities.enums;

/**
 * Created by vitaly on 13.05.15.
 */
public enum CommunicationTypeEnum {
    MOBILE("Mobile"),
    WORK("Work"),
    HOME("Home");

    private String description;

    CommunicationTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
//    @Override
//    public String toString() {
//        return description;
//    }
}
