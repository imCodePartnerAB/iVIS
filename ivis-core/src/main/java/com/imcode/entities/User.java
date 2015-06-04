package com.imcode.entities;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaly on 12.05.15.
 */
@Entity
@Table(name="dbo_user")
//@AttributeOverrides({
        @AttributeOverride(name="name", column = @Column(unique = true, length = 150, nullable = false))
//})
public class User extends AbstractNamedEntity implements UserDetails, Serializable {
    public static final String DEFAULT_PASSWORD = "";

//    @NotNull(message = "{user.requiredPassword}")
//    @Size(min = 3, max = 15)
    @Column(nullable = false, columnDefinition = DEFAULT_PASSWORD)
    private String password = DEFAULT_PASSWORD;

    @Transient
    private String confirmPassword = DEFAULT_PASSWORD;

    @Column
    private Boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Person person;

//    @Column(name="firstName")
//    private String firstName;
//
//    @Column(name="lastName")
//    private String lastName;
//
//    @Column(name="bitrhday")
//    private Date bitrhday;

//    @Column(name="email")
//    private String email;
//
//    @Column(name="phoneNumber")
//    private String phoneNumber;
//
//    @Column(name="streetAddress")
//    private String streetAddress;
//
//    @Column(name="postalCode")
//    private String postalCode;
//
//    @Column(name="city")
//    private String city;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name="school")
//    private School school;
//
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name="schoolClass")
//    private SchoolClass schoolClass;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_user_roles_cross",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, Boolean enabled, Set<Role> roles) {
        this.name = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(String username, String password, Role... roles) {
        this.name = username;
        this.password = password;
        Collections.addAll(this.roles, roles);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }
//
//    public String getPwd() {
//        return pwd;
//    }
//
//    public void setPwd(String pwd) {
//        this.pwd = pwd;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public Date getBitrhday() {
//        return bitrhday;
//    }
//
//    public void setBitrhday(Date bitrhday) {
//        this.bitrhday = bitrhday;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getStreetAddress() {
//        return streetAddress;
//    }
//
//    public void setStreetAddress(String streetAddress) {
//        this.streetAddress = streetAddress;
//    }
//
//    public String getPostalCode() {
//        return postalCode;
//    }
//
//    public void setPostalCode(String postalCode) {
//        this.postalCode = postalCode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public School getSchool() {
//        return school;
//    }
//
//    public void setSchool(School school) {
//        this.school = school;
//    }
//
//    public SchoolClass getSchoolClass() {
//        return schoolClass;
//    }
//
//    public void setSchoolClass(SchoolClass schoolClass) {
//        this.schoolClass = schoolClass;
//    }


    @Override
    public String getUsername() {
        return name;
    }

    public void setUsername(String username) {
        this.name = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    public void setAuthorities(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(name).append('\'');
        sb.append(", pwd='").append(password).append('\'');
//        sb.append(", firstName='").append(firstName).append('\'');
//        sb.append(", lastName='").append(lastName).append('\'');
//        sb.append(", bitrhday=").append(bitrhday);
//        sb.append(", email='").append(email).append('\'');
//        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
//        sb.append(", streetAddress='").append(streetAddress).append('\'');
//        sb.append(", postalCode='").append(postalCode).append('\'');
//        sb.append(", city='").append(city).append('\'');
//        sb.append(", school=").append(school);
//        sb.append(", schoolClass=").append(schoolClass);
        sb.append('}');
        return sb.toString();
    }

    //Setter utility
    public void setAuthorities(Role role) {
        this.roles = new HashSet<>(Arrays.asList(role));
    }
}
