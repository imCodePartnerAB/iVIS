package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by vitaly on 13.05.15.
 */
@Entity
@Table(name = "dbo_academic_year")
public class AcademicYear extends AbstractNamedEntity<Long> implements Serializable {
    public AcademicYear() {
    }

    public AcademicYear(String name) {
        super(name);
    }
}
