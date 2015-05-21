package com.imcode.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_after_school_center_section")
public class AfterSchoolCenterSection extends AbstractNamedEntity{
    public AfterSchoolCenterSection() {
    }

    public AfterSchoolCenterSection(String name) {
        super(name);
    }

}
