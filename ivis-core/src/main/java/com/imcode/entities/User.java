package com.imcode.entities;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;

/**
 * Created by vitaly on 12.05.15.
 */
@Entity
@Table(name="dbo_user")
//@AttributeOverrides({
        @AttributeOverride(name="name", column = @Column(unique = true, length = 150, nullable = false))
//})
public class User extends AbstractNamedEntity<Long> implements UserDetails, Serializable {
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    public void setAuthorities(Set<Role> roles) {
        this.roles = roles;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
