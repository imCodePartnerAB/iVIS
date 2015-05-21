package com.imcode.entities.oauth2;

import com.imcode.entities.AbstractNamedEntity;
import com.imcode.entities.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_oauth_client_role")
@AttributeOverrides(@AttributeOverride(name = "name", column = @Column(name = "authority", nullable = false, length = 100, unique = true)))
public class ClientRole extends AbstractNamedEntity implements GrantedAuthority {
    public ClientRole() {
    }

    public ClientRole(String name) {
        super(name);
    }

    @Override
    public String getAuthority() {
        return name;
    }


}
