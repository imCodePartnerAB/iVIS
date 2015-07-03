package com.imcode.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_after_school_center_section")
public class AfterSchoolCenterSection extends AbstractNamedEntity<Long> implements Serializable {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "schoolId")
    private School school;

    public AfterSchoolCenterSection() {
    }

    public AfterSchoolCenterSection(String name) {
        super(name);
    }

    public AfterSchoolCenterSection(Long id, String name) {
        super(id, name);
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
