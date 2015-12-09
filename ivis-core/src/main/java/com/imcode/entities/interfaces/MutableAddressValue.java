package com.imcode.entities.interfaces;

/**
 * Created by vitaly on 01.12.15.
 */
public interface MutableAddressValue<T extends Enum<T>> extends ImmutableAddressValue<T> {
    void setValue(String value);

    void setType(T type);
}
