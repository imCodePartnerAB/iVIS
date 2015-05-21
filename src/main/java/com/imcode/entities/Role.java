package com.imcode.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "dbo_role")
@AttributeOverrides(@AttributeOverride(name = "name", column = @Column(name = "authority", nullable = false, length = 100, unique = true)))
public class Role extends AbstractNamedEntity implements GrantedAuthority{

    public Role() { }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String getAuthority() {
        return name;
    }

}

