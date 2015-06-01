package com.imcode.entities.oauth2;

import com.imcode.entities.AbstractNamedEntity;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_oauth_client_role")
@AttributeOverrides(@AttributeOverride(name = "name", column = @Column(name = "authority", nullable = false, length = 100, unique = true)))
public class ClientRole extends AbstractNamedEntity implements GrantedAuthority, Serializable{
    public ClientRole() {
    }


    public ClientRole(String name) {
        super(name);
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
