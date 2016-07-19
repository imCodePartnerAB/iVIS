package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.embed.Address;
import com.imcode.entities.embed.Email;
import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.AddressTypeEnum;
import com.imcode.entities.enums.CommunicationTypeEnum;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractPerson;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_contact_person")
public class ContactPerson extends AbstractPerson implements Serializable, JpaPersonalizedEntity {
    public static final String TABLE_SUFFIX = "contact_person";
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "person_id")
//    @Transient
    private Person person;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_pupil_guardians_cross",
            joinColumns = @JoinColumn(name = "guardian_id"),
            inverseJoinColumns = @JoinColumn(name = "pupil_id"))
    private Set<Pupil> pupils;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_" + TABLE_SUFFIX + "_address", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<AddressTypeEnum, Address> addresses = new EnumMap<>(AddressTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_" + TABLE_SUFFIX + "_email", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<CommunicationTypeEnum, Email> emails = new EnumMap<>(CommunicationTypeEnum.class);

    @LazyCollection(LazyCollectionOption.FALSE)
    @ElementCollection
    @CollectionTable(name = "dbo_" + TABLE_SUFFIX + "_phone", joinColumns = @JoinColumn(name = "owner_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "type_key", length = 50)
    private Map<CommunicationTypeEnum, Phone> phones = new EnumMap<>(CommunicationTypeEnum.class);


    //Comunication information
    public Map<AddressTypeEnum, Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Map<AddressTypeEnum, Address> addresses) {
//        this.addresses = convertToEnumMap(addresses, AddressTypeEnum.class);
        this.addresses = addresses;
    }

    @JsonIgnore
    public void setAddress(Address address) {
//        EnumMap<AddressTypeEnum, Address> map = (EnumMap<AddressTypeEnum, Address>) this.addresses;
        putAddressValueIntoMap(AddressTypeEnum.class, address, addresses);
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
//        this.emails = convertToEnumMap(emails, CommunicationTypeEnum.class);
        this.emails = emails;
    }

    @JsonIgnore
    public void setEmail(Email email) {
//        EnumMap<CommunicationTypeEnum, Email> map = (EnumMap<CommunicationTypeEnum, Email>) this.emails;
        putAddressValueIntoMap(CommunicationTypeEnum.class, email, emails);
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
//        this.phones = convertToEnumMap(phones, CommunicationTypeEnum.class);
        this.phones = phones;
    }

    @JsonIgnore
    public void setPhone(Phone phone) {
//        EnumMap<CommunicationTypeEnum, Phone> map = (EnumMap<CommunicationTypeEnum, Phone>) this.phones;
        putAddressValueIntoMap(CommunicationTypeEnum.class, phone, phones);
    }

    @JsonIgnore
    public Phone getPhone(CommunicationTypeEnum type) {
        Objects.requireNonNull(type);

        if (emails == null) {
            return null;
        }

        return phones.get(type);
    }
    public ContactPerson() {
    }

    public ContactPerson(String pid, String firstName, String lastName) {
        super(pid, firstName, lastName);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

    @Override
    public String toString() {
        return super.toString();
//        return person != null ? person.toString() : "";
    }
}
