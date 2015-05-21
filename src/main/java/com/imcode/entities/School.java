package com.imcode.entities;

import com.imcode.entities.enums.ServiceTypeEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="dbo_school")
public class School extends AbstractNamedEntity{
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
