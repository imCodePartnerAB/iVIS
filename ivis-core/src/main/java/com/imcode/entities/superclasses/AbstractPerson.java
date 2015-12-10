package com.imcode.entities.superclasses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.interfaces.JpaContactedPerson;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by vitaly on 13.05.15.
 */
//@Entity
//@Table(name = "dbo_person")
@MappedSuperclass
public abstract class AbstractPerson extends AbstractIdEntity<Long> implements Serializable, JpaPersonalizedEntity, JpaContactedPerson {
    @Column
    private String personalId;

    @Column
    private String firstName;

    @Column
    private String lastName;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ElementCollection
//    @CollectionTable(name = "dbo_person_address", joinColumns = @JoinColumn(name = "ownerId"))
//    @MapKeyEnumerated(EnumType.STRING)
//    @MapKeyColumn(name = "typeKey", length = 50)
//    private Map<AddressTypeEnum, Address> addresses;
//
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ElementCollection
//    @CollectionTable(name = "dbo_person_email", joinColumns = @JoinColumn(name = "ownerId"))
//    @MapKeyEnumerated(EnumType.STRING)
//    @MapKeyColumn(name = "typeKey", length = 50)
//    private Map<CommunicationTypeEnum, Email> emails;
//
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @ElementCollection
//    @CollectionTable(name = "dbo_person_phone", joinColumns = @JoinColumn(name = "ownerId"))
//    @MapKeyEnumerated(EnumType.STRING)
//    @MapKeyColumn(name = "typeKey", length = 50)
//    private Map<CommunicationTypeEnum, Phone> phones;

    public AbstractPerson() {
    }

    public AbstractPerson(String pid, String firstName, String lastName) {
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

//    //Comunication information
//    public Map<AddressTypeEnum, Address> getAddresses() {
//        return addresses;
//    }
//
//    public void setAddresses(Map<AddressTypeEnum, Address> addresses) {
////        this.addresses = convertToEnumMap(addresses, AddressTypeEnum.class);
//        this.addresses = addresses;
//    }
//
//    @JsonIgnore
//    public void setAddress(Address address) {
////        EnumMap<AddressTypeEnum, Address> map = (EnumMap<AddressTypeEnum, Address>) this.addresses;
//        putAddressValueIntoMap(AddressTypeEnum.class, address, addresses);
//    }
//
//    @JsonIgnore
//    public Address getAddress(AddressTypeEnum addressType) {
//        Objects.requireNonNull(addressType);
//
//        if (addresses == null) {
//            return null;
//        }
//
//        return addresses.get(addressType);
//    }
//
//    public Map<CommunicationTypeEnum, Email> getEmails() {
//        return emails;
//    }
//
//    public void setEmails(Map<CommunicationTypeEnum, Email> emails) {
////        this.emails = convertToEnumMap(emails, CommunicationTypeEnum.class);
//        this.emails = emails;
//    }
//
//    @JsonIgnore
//    public void setEmail(Email email) {
////        EnumMap<CommunicationTypeEnum, Email> map = (EnumMap<CommunicationTypeEnum, Email>) this.emails;
//        putAddressValueIntoMap(CommunicationTypeEnum.class, email, emails);
//    }
//
//    @JsonIgnore
//    public Email getEmail(CommunicationTypeEnum type) {
//        Objects.requireNonNull(type);
//
//        if (emails == null) {
//            return null;
//        }
//
//        return emails.get(type);
//    }
//
//    public Map<CommunicationTypeEnum, Phone> getPhones() {
//        return phones;
//    }
//
//    public void setPhones(Map<CommunicationTypeEnum, Phone> phones) {
////        this.phones = convertToEnumMap(phones, CommunicationTypeEnum.class);
//        this.phones = phones;
//    }
//
//    @JsonIgnore
//    public void setPhone(Phone phone) {
////        EnumMap<CommunicationTypeEnum, Phone> map = (EnumMap<CommunicationTypeEnum, Phone>) this.phones;
//        putAddressValueIntoMap(CommunicationTypeEnum.class, phone, phones);
//    }
//
//    @JsonIgnore
//    public Phone getPhone(CommunicationTypeEnum type) {
//        Objects.requireNonNull(type);
//
//        if (emails == null) {
//            return null;
//        }
//
//        return phones.get(type);
//    }

//    public static AbstractPerson fromString(String firstNameLastName) {
//        if (firstNameLastName == null || firstNameLastName.isEmpty()) {
//            throw new IllegalArgumentException("The \"firstNameLastName\" should be not epmty!");
//        }
//
//        AbstractPerson person = new AbstractPerson();
//
//        String[] parts = firstNameLastName.split(" ");
//
//        person.setFirstName(parts[0]);
//        try {
//            person.setLastName(parts[1]);
//        } catch (Exception ignore) {
//        }
//
//
//        return person;
//    }

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

//    private <K extends Enum<K>, V> EnumMap<K, V> convertToEnumMap(Map<K, V> newValue, Class<K> type) {
//        if (!(newValue instanceof EnumMap)) {
//            if (newValue == null || newValue.isEmpty()) {
//                return new EnumMap<>(type);
//            } else {
//                return new EnumMap<>(newValue);
//            }
//
//        }
//
//        return (EnumMap<K, V>) newValue;
//    }

    protected  <K extends Enum<K>, V extends AbstractAddressValue<K>> void putAddressValueIntoMap(Class<K> enumClass, V addressValue, Map<K, V> map) {
        Objects.requireNonNull(addressValue);
        K addressValueType = addressValue.getType();
        Objects.requireNonNull(addressValueType);

        if (map == null) {
            map = new EnumMap<>(enumClass);
        }

        map.put(addressValueType, addressValue);
    }
}
