package com.imcode.entities.embed;

import com.imcode.entities.enums.CommunicationTypeEnum;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
//@Entity
//@Table(name = "dbo_email")
@Embeddable
public class Email implements Serializable {
    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    private CommunicationTypeEnum communicationType;

    public Email() {
    }

    public Email(String address, CommunicationTypeEnum communicationType) {
        this.address = address;
        this.communicationType = communicationType;
    }

    public Email(String address) {
        this.address = address;
        this.communicationType = CommunicationTypeEnum.MOBILE;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CommunicationTypeEnum getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(CommunicationTypeEnum communicationType) {
        this.communicationType = communicationType;
    }

    @Override
    public String toString() {
        return address;
    }
}
