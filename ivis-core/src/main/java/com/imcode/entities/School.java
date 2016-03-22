package com.imcode.entities;

import com.fasterxml.jackson.annotation.*;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dbo_school")
public class School extends AbstractNamedEntity<Long> implements Serializable {
    @Column
    private String schoolId;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "dbo_school_service_cross")
    private Set<ServiceTypeEnum> services;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "school", cascade = {CascadeType.REFRESH})
//    @JsonManagedReference
    private Set<SchoolClass> schoolClasses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "school")
    private Set<AfterSchoolCenterSection> afterSchoolCenterSections;

    public Set<ServiceTypeEnum> getServices() {
        return services;
    }

    public void setServices(Set<ServiceTypeEnum> services) {
        this.services = services;
    }

    @JsonIgnore
    public void setServices(ServiceTypeEnum... services) {
        this.services = new HashSet<>(Arrays.asList(services));
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }


    public Set<SchoolClass> getSchoolClasses() {
        return schoolClasses;
    }

    public void setSchoolClasses(Set<SchoolClass> schoolClasses) {
        this.schoolClasses = schoolClasses;
    }

    public Set<AfterSchoolCenterSection> getAfterSchoolCenterSections() {
        return afterSchoolCenterSections;
    }

    public void setAfterSchoolCenterSections(Set<AfterSchoolCenterSection> afterSchoolCenterSections) {
        this.afterSchoolCenterSections = afterSchoolCenterSections;
    }

    @Override
    public String toString() {
        return name;
    }
}
