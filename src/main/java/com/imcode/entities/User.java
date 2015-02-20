package com.imcode.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="dbo_user")
public class User {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="login")
    private String login;

    @Column(name="pwd")
    private String pwd;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="bitrhday")
    private Date bitrhday;

    @Column(name="email")
    private String email;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="streetAddress")
    private String streetAddress;

    @Column(name="postalCode")
    private String postalCode;

    @Column(name="city")
    private String city;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="school")
    private School school;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="schoolClass")
    private SchoolClass schoolClass;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "dbo_usersInRoles",
            joinColumns = @JoinColumn(name = "user"),
            inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public Date getBitrhday() {
        return bitrhday;
    }

    public void setBitrhday(Date bitrhday) {
        this.bitrhday = bitrhday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", pwd='").append(pwd).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", bitrhday=").append(bitrhday);
        sb.append(", email='").append(email).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", streetAddress='").append(streetAddress).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", school=").append(school);
        sb.append(", schoolClass=").append(schoolClass);
        sb.append('}');
        return sb.toString();
    }
}
