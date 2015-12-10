package com.imcode.entities.interfaces;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.Person;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vitaly on 09.09.15.
 */
public interface JpaContactedPerson extends JpaPersonalizedEntity {
    Map<AddressTypeEnum, Address> getAddresses();

    void setAddresses(Map<AddressTypeEnum, Address> addresses);

    @JsonIgnore
    void setAddress(Address address);

    @JsonIgnore
    Address getAddress(AddressTypeEnum addressType);

    Map<CommunicationTypeEnum, Email> getEmails();

    void setEmails(Map<CommunicationTypeEnum, Email> emails);

    @JsonIgnore
    void setEmail(Email email);

    @JsonIgnore
    Email getEmail(CommunicationTypeEnum type);

    Map<CommunicationTypeEnum, Phone> getPhones();

    void setPhones(Map<CommunicationTypeEnum, Phone> phones);

    @JsonIgnore
    void setPhone(Phone phone);

    @JsonIgnore
    Phone getPhone(CommunicationTypeEnum type);
}
