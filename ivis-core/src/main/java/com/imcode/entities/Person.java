package com.imcode.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_person")
public class Person extends AbstractIdEntity  implements Serializable {
    @Column
    private Long personalIdentificationNumber;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    @JoinTable(name = "dbo_person_address_cross",
            joinColumns = @JoinColumn(name = "personId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "addressId", referencedColumnName = "id"))
    private Set<Address> addresses;

    @ManyToMany
    @JoinTable(name = "dbo_person_email_cross",
            joinColumns = @JoinColumn(name = "personId"),
            inverseJoinColumns = @JoinColumn(name = "emailId"))
    private Set<Email> emails;

    @ManyToMany
    @JoinTable(name = "dbo_person_phone_cross",
            joinColumns = @JoinColumn(name = "personId"),
            inverseJoinColumns = @JoinColumn(name = "phoneId"))
    private Set<Phone> phones;

    public Person(){
    }

    public Person(long pid, String firstName, String lastName) {
        this.personalIdentificationNumber = pid;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getPersonalIdentificationNumber() {
        return personalIdentificationNumber;
    }

    public void setPersonalIdentificationNumber(Long personalIdentificationNumber) {
        this.personalIdentificationNumber = personalIdentificationNumber;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
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
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
