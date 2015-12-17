package com.imcode.entities.embed;

import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.superclasses.ContactInformation;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
//@Entity
//@Table(name = "dbo_phone")
@Embeddable
//public class Phone extends AbstractIdEntity  implements Serializable {
public class Phone extends ContactInformation<CommunicationTypeEnum> implements Serializable {
//    @Column
//    private String number;
//
//    @Enumerated(EnumType.STRING)
//    private CommunicationTypeEnum communicationType;

    public static Phone of(CommunicationTypeEnum type) {
        return new Phone(type, null);
    }

    public static Phone of(CommunicationTypeEnum type, String value) {
        return new Phone(type, value);
    }

    public Phone() {
        super(CommunicationTypeEnum.HOME, null);
    }

    protected Phone(CommunicationTypeEnum type, String value) {
        super(type, value);
    }

    @Deprecated
    public String getNumber() {
        return value;
    }

    @Deprecated
    public void setNumber(String value) {
        this.value = value;
    }

    @Deprecated
    public CommunicationTypeEnum getCommunicationType() {
        return type;
    }

    @Deprecated
    public void setCommunicationType(CommunicationTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return value;
    }
}
