package com.imcode.entities.interfaces;

/**
 * Created by vitaly on 01.12.15.
 */
public interface ImmutableAddressValue<T extends Enum<T>> {
    String getValue();

    T getType();
}
