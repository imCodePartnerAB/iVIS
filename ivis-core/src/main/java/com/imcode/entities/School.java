package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.enums.ServiceTypeEnum;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dbo_school")
@AttributeOverride(name = "name", column = @Column(length = 200, nullable = false) )
public class School extends AbstractNamedEntity<Long> implements Serializable {

    @NotNull(message = "school id can not be null")
    @Column(name = "school_id")
    private String schoolId;

    @NotNull(message = "services can not be null")
    @Size(min = 1, message = "at least 1  service must be check")
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @JoinTable(name = "dbo_school_service_cross", joinColumns = @JoinColumn(name = "school_id"))
    private Set<ServiceTypeEnum> services;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "school", cascade = {CascadeType.REFRESH})
    private Set<SchoolClass> schoolClasses;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "school")
    private Set<AfterSchoolCenterSection> afterSchoolCenterSections;

    @Column(name = "next_cloud_enabled", columnDefinition = "BIT DEFAULT FALSE")
    private Boolean nextCloudEnabled = false;

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

    public Boolean getNextCloudEnabled() {
        return nextCloudEnabled;
    }

    public void setNextCloudEnabled(Boolean nextCloudEnabled) {
        this.nextCloudEnabled = nextCloudEnabled;
    }

    @Override
    public String toString() {
        return name;
    }
}
