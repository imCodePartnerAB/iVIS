package com.imcode.entities.enums;

/**
 * Created by vitaly on 13.05.15.
 */
public enum AddressTypeEnum {
    REGISTERED(0, "Registered address"),
    RESIDENTIAL(1, "Residential address"),
    BOARDER(2, "Boarder address");

    private String representation;
    private int id;

    AddressTypeEnum(int id, String representation) {
        this.representation = representation;
    }

    // the identifierMethod
    public int toInt() {
        return id;
    }

    // the valueOfMethod
    public  static AddressTypeEnum fromInt(int id) {
        for (AddressTypeEnum addressTypeEnum : values()) {
            if (addressTypeEnum.id == id)
                return addressTypeEnum;
        }

        throw new IllegalArgumentException();
    }

//    @Override
//    public String toString() {
//        return representation;
//    }

    public static void main(String[] args) {
        System.out.println(AddressTypeEnum.valueOf("RESIDENTIAL"));
    }
}
