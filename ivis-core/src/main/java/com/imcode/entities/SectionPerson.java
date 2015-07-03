package com.imcode.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by vitaly on 14.05.15.
 */
//@Entity
//@Table(name = "dbo_section_person")
    @Deprecated
public class SectionPerson extends AbstractIdEntity<Long>  implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "afterSchoolCenterSectionId")
    private AfterSchoolCenterSection afterSchoolCenterSection;

    @OneToMany(mappedBy = "sectionPerson")
    private Set<AfterSchoolCenterSchema> afterSchoolCenterSchema;

    public SectionPerson() {
    }

    public SectionPerson(Person person, AfterSchoolCenterSection afterSchoolCenterSection) {
        this.person = person;
        this.afterSchoolCenterSection = afterSchoolCenterSection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public AfterSchoolCenterSection getAfterSchoolCenterSection() {
        return afterSchoolCenterSection;
    }

    public void setAfterSchoolCenterSection(AfterSchoolCenterSection afterSchoolCenterSection) {
        this.afterSchoolCenterSection = afterSchoolCenterSection;
    }

    public Set<AfterSchoolCenterSchema> getAfterSchoolCenterSchema() {
        return afterSchoolCenterSchema;
    }

    public void setAfterSchoolCenterSchema(Set<AfterSchoolCenterSchema> afterSchoolCenterSchema) {
        this.afterSchoolCenterSchema = afterSchoolCenterSchema;
    }
}
