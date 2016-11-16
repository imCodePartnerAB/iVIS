package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "dbo_role")
@AttributeOverrides(@AttributeOverride(name = "name", column = @Column(name = "authority", nullable = false, length = 100, unique = true)))
public class Role extends AbstractNamedEntity<Long> implements GrantedAuthority, Serializable{

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "dbo_role_permission_cross",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    @Column
    private Boolean internal;

    @Column(name = "user_role")
    private Boolean userRole;

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Boolean isUserRole() {
        return userRole;
    }

    public Boolean getUserRole() {
        return userRole;
    }

    public void setUserRole(Boolean userRole) {
        this.userRole = userRole;
    }
}

