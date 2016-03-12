package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.embed.Email;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.superclasses.ContactInformation;
import com.imcode.entities.superclasses.AbstractPerson;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_person")
public class Person extends AbstractPerson implements Serializable {

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_address", joinColumns = @JoinColumn(name = "ownerId"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "typeKey", length = 50)
    private Map<AddressTypeEnum, Address> addresses = new EnumMap<>(AddressTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_email", joinColumns = @JoinColumn(name = "ownerId"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "typeKey", length = 50)
    private Map<CommunicationTypeEnum, Email> emails = new EnumMap<>(CommunicationTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_phone", joinColumns = @JoinColumn(name = "ownerId"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "typeKey", length = 50)
    private Map<CommunicationTypeEnum, Phone> phones = new EnumMap<>(CommunicationTypeEnum.class);

    public Person() {
    }

    public Person(String pid, String firstName, String lastName) {
        super(pid, firstName, lastName);
    }

    //todo Убрать это
    @Override
    public Person getPerson() {
        return this;
    }

    @Override
    public void setPerson(Person person) {

    }

    //Comunication information
    public Map<AddressTypeEnum, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<AddressTypeEnum, Address> addresses) {
        this.addresses = convertToEnumMap(addresses, AddressTypeEnum.class);
    }

    @JsonIgnore
    public void setAddress(Address address) {
        EnumMap<AddressTypeEnum, Address> map = (EnumMap<AddressTypeEnum, Address>) this.addresses;
        putAddressValueIntoMap(AddressTypeEnum.class, address, map);
    }

    @JsonIgnore
    public Address getAddress(AddressTypeEnum addressType) {
        Objects.requireNonNull(addressType);

        if (addresses == null) {
            return null;
        }

        return addresses.get(addressType);
    }

    public Map<CommunicationTypeEnum, Email> getEmails() {
        return emails;
    }

    public void setEmails(Map<CommunicationTypeEnum, Email> emails) {
        this.emails = convertToEnumMap(emails, CommunicationTypeEnum.class);
    }

    @JsonIgnore
    public void setEmail(Email email) {
        EnumMap<CommunicationTypeEnum, Email> map = (EnumMap<CommunicationTypeEnum, Email>) this.emails;
        putAddressValueIntoMap(CommunicationTypeEnum.class, email, map);
    }

    @JsonIgnore
    public Email getEmail(CommunicationTypeEnum type) {
        Objects.requireNonNull(type);

        if (emails == null) {
            return null;
        }

        return emails.get(type);
    }

    public Map<CommunicationTypeEnum, Phone> getPhones() {
        return phones;
    }

    public void setPhones(Map<CommunicationTypeEnum, Phone> phones) {
        this.phones = convertToEnumMap(phones, CommunicationTypeEnum.class);
    }

    @JsonIgnore
    public void setPhone(Phone phone) {
        EnumMap<CommunicationTypeEnum, Phone> map = (EnumMap<CommunicationTypeEnum, Phone>) this.phones;
        putAddressValueIntoMap(CommunicationTypeEnum.class, phone, map);
    }

    @JsonIgnore
    public Phone getPhone(CommunicationTypeEnum type) {
        Objects.requireNonNull(type);

        if (emails == null) {
            return null;
        }

        return phones.get(type);
    }

    public static Person fromString(String firstNameLastName) {
        if (firstNameLastName == null || firstNameLastName.isEmpty()) {
            throw new IllegalArgumentException("The \"firstNameLastName\" should be not epmty!");
        }

        Person person = new Person();

        String[] parts = firstNameLastName.split(" ");

        person.setFirstName(parts[0]);
        try {
            person.setLastName(parts[1]);
        } catch (Exception ignore) {
        }


        return person;
    }

    private void addWord(StringBuilder sb, String word) {
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) != ' ') {
            sb.append(' ');
        }

        sb.append(word);
    }

    private <K extends Enum<K>, V> EnumMap<K, V> convertToEnumMap(Map<K, V> newValue, Class<K> type) {
        if (!(newValue instanceof EnumMap)) {
            if (newValue == null || newValue.isEmpty()) {
                return new EnumMap<>(type);
            } else {
                return new EnumMap<>(newValue);
            }

        }

        return (EnumMap<K, V>) newValue;
    }

}
