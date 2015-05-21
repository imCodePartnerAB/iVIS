package com.imcode.entities.enums;

/**
 * Created by vitaly on 15.05.15.
 */
public enum AuthorizedGrantType {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials"),
    PASSWORD("password");

    private String representation;

    AuthorizedGrantType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public static String[] getRepresentations() {
        AuthorizedGrantType[] values = values();
        String[] strings = new String[values.length];
//        AuthorizedGrantType[] values = values();
        for (int i = 0; i < values.length; i++) {
            AuthorizedGrantType value = values[i];
            strings[i] = value.getRepresentation();
        }

        return strings;
    }

    @Override
    public String toString() {
        return representation;
    }
}
