package com.imcode.entities.enums;

/**
 * Created by vitaly on 13.05.15.
 */
public enum PersonTypeEnum {
    PUPIL("Mobile"),
    GUARDIAN("Guardian"),
    CONTACT_PERSON("Contact person"),
    TEACHER("Teacher"),
    ADMINISTRATOR("Administrator");

    private String representation;

    PersonTypeEnum(String representation) {
        this.representation = representation;
    }

    @Override
    public String toString() {
        return representation;
    }
}
