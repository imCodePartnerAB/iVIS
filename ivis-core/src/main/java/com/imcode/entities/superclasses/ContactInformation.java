package com.imcode.entities.superclasses;

import com.imcode.entities.interfaces.MutableAddressValue;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by vitaly on 01.12.15.
 */
@MappedSuperclass
public abstract class ContactInformation<T extends Enum<T>> implements MutableAddressValue<T>, Serializable {
    @Enumerated(EnumType.STRING)
    protected T type;

    @Column
    protected String value;

    public ContactInformation() {
    }

//    protected ContactInformation(T type) {
//        this.type = type;
//    }

    protected ContactInformation(T type, String value) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String address) {
        this.value = address;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInformation<T> that = (ContactInformation<T>) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
