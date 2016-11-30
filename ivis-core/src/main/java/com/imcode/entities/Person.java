package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.embed.Email;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.entities.superclasses.ContactInformation;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_person")
public class Person extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity {

    @Column(name = "personal_id")
    private String personalId;

    @NotNull(message = "firstName is required")
    @Size(min = 4, message = "at least firstName must have 4 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "lastName is required")
    @Size(min = 4, message = "at least lastName must have 4 characters")
    @Column(name = "last_name")
    private String lastName;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_address", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<AddressTypeEnum, Address> addresses = new EnumMap<>(AddressTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_email", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<CommunicationTypeEnum, Email> emails = new EnumMap<>(CommunicationTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_person_phone", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<CommunicationTypeEnum, Phone> phones = new EnumMap<>(CommunicationTypeEnum.class);

    public Person() {
    }

    public Person(String pid, String firstName, String lastName) {
        this.personalId = pid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    @JsonIgnore
    public Person getPerson() {
        return this;
    }

    @Override
    @JsonIgnore
    public void setPerson(Person person) {
        throw new UnsupportedOperationException();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (StringUtils.hasText(firstName))
            addWord(sb, firstName);

        if (StringUtils.hasText(lastName))
            addWord(sb, lastName);


        if (sb.length() == 0)
            addWord(sb, personalId);
        ;

        return sb.toString();
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

    protected  static <K extends Enum<K>, V extends ContactInformation<K>> void putAddressValueIntoMap(Class<K> enumClass, V addressValue, Map<K, V> map) {
        Objects.requireNonNull(addressValue);
        K addressValueType = addressValue.getType();
        Objects.requireNonNull(addressValueType);

        if (map == null) {
            map = new EnumMap<>(enumClass);
        }

        map.put(addressValueType, addressValue);
    }

}
