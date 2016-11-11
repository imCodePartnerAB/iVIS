package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.enums.Scope;
import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by vitaly on 12.05.15.
 */
@Entity
@Table(name="dbo_user")
//@AttributeOverrides({
        @AttributeOverride(name="name", column = @Column(unique = true, length = 150, nullable = false))
//})
public class User extends AbstractNamedEntity<Long> implements UserDetails, Serializable, JpaPersonalizedEntity {
    public static final String DEFAULT_PASSWORD = "";

    @NotNull(message = "password is required")
    @Size(min = 8, message = "password min 8 characters")
    @Column(nullable = false, columnDefinition = DEFAULT_PASSWORD)
    @JsonIgnore
    private String password = DEFAULT_PASSWORD;

    @Transient
    @JsonIgnore
    private String confirmPassword = DEFAULT_PASSWORD;

    @Column
    private Boolean enabled = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    @NotNull(message = "roles is required")
    @Size(min = 1, message = "at least 1 role must be checked")
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_user_roles_cross",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Column(columnDefinition = "text")
    private String scope;

    @Column(columnDefinition = "text")
    private String allowedEntities;

    @Column(name = "saml2_id")
    private String saml2Id;

    @JsonIgnore
    public Boolean getEnabled() {
        return enabled;
    }

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
    @JsonIgnore
    public String getUsername() {
        return name;
    }

    @JsonIgnore
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAllowedEntities() {
        return allowedEntities;
    }

    public void setAllowedEntities(String allowedEntities) {
        this.allowedEntities = allowedEntities;
    }

    @Override
    @JsonIgnore
    public Set<Role> getAuthorities() {
        return roles;
    }

    @JsonIgnore
    public void setAuthorities(Set<Role> roles) {
        this.roles = roles;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonIgnore
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @JsonIgnore
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return name == null ? Long.toString(id) : name;
    }

    public String getSaml2Id() {
        return saml2Id;
    }

    public void setSaml2Id(String saml2Id) {
        this.saml2Id = saml2Id;
    }

    //Setter utility
    @JsonIgnore
    public void setAuthorities(Role role) {
        this.roles = new HashSet<>(Collections.singleton(role));
    }

    //Utilities
    public boolean hasRoles(String... roleNames) {
        Objects.requireNonNull(roleNames);
        return getRoleNames().containsAll(Arrays.asList(roleNames));
    }

    public boolean hasRoles(Role... requiredRoles) {
        Objects.requireNonNull(requiredRoles);
        return roles.containsAll(Arrays.asList(requiredRoles));
    }

    @JsonIgnore
    public Set<String> getRoleNames() {
        return roles.stream().map(Role::getAuthority).collect(Collectors.toSet());
    }

    @JsonIgnore
    public String[] getScopeArray() {
        return scope.split(",");
    }

    @JsonIgnore
    public Scope[] getScopeEnum() {
        String[] split = scope.split(",");
        return Arrays.stream(split)
                .map(s -> Scope.valueOf(s.toUpperCase()))
                .toArray(Scope[]::new);
    }

    @JsonIgnore
    public void addScope(Scope scope) {
        this.scope += "," + scope.toString().toLowerCase();
    }

    @JsonIgnore
    public void deleteScope(Scope scope) {
        String[] scopeArray = getScopeArray();
        this.scope = Arrays.stream(scopeArray)
                .filter(s -> !s.equals(scope.toString().toLowerCase()))
                .collect(Collectors.joining(","));
    }

    @JsonIgnore
    public void setScopeEnum(Scope [] scope) {
        this.scope = Arrays.stream(scope)
                .map(s -> s.toString().toLowerCase())
                .collect(Collectors.joining(","));
    }

    @JsonIgnore
    public List<String> getAllowedEntitiesList() {
        String[] split = allowedEntities.split(",");
        return Arrays.asList(split);
    }

    @JsonIgnore
    public void setAllowedEntities(List<String> allowedEntities) {
        this.allowedEntities = allowedEntities.stream().collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1l, "ROLE_ADMIN"));
        roles.add(new Role(2l, "ROLE_USER"));
        roles.add(new Role(3l, "ROLE_GUARDIAN"));
        roles.add(new Role(4l, "ROLE_STUDENT"));
        Role roleAdmin = new Role("ROLE_ADMIN");
        User vitaly = new User("Admin", "pass", true, roles);
        System.out.println(roles.contains(roleAdmin));
    }


}
