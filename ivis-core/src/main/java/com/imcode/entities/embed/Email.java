package com.imcode.entities.embed;

import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.superclasses.AbstractAddressValue;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
//@Entity
//@Table(name = "dbo_email")
@Embeddable
public class Email extends AbstractAddressValue<CommunicationTypeEnum> implements Serializable {
//    @Column
//    private String address;
//
//    @Enumerated(EnumType.STRING)
//    private CommunicationTypeEnum communicationType;

    public static Email of(CommunicationTypeEnum type) {
        return new Email(type, null);
    }

    public static Email of(CommunicationTypeEnum type, String value) {
        return new Email(type, value);
    }

    public Email() {
        super(CommunicationTypeEnum.HOME, null);
    }

    protected Email(CommunicationTypeEnum type, String value) {
        super(type, value);
    }

    //    public Email(String address, CommunicationTypeEnum communicationType) {
//        this.address = address;
//        this.communicationType = communicationType;
//    }
//
//    public Email(String address) {
//        this.address = address;
//        this.communicationType = CommunicationTypeEnum.MOBILE;
//    }
    @Deprecated
    public String getAddress() {
        return value;
    }

    @Deprecated
    public void setAddress(String address) {
        this.value = address;
    }

    @Deprecated
    public CommunicationTypeEnum getCommunicationType() {
        return type;
    }

    @Deprecated
    public void setCommunicationType(CommunicationTypeEnum communicationType) {
        this.type = communicationType;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Email email = (Email) o;
//
//        if (address != null ? !address.equals(email.address) : email.address != null) return false;
//        return communicationType == email.communicationType;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = address != null ? address.hashCode() : 0;
//        result = 31 * result + (communicationType != null ? communicationType.hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return value;
    }
}
