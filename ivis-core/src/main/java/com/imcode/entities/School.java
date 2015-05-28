package com.imcode.entities;

import com.imcode.entities.enums.ServiceTypeEnum;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name="dbo_school")
public class School extends AbstractNamedEntity  implements Serializable {
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "dbo_school_service_cross")
    private Set<ServiceTypeEnum> services;

    public Set<ServiceTypeEnum> getServices() {
        return services;
    }

    public void setServices(Set<ServiceTypeEnum> services) {
        this.services = services;
    }
}
