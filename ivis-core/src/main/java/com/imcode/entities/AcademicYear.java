package com.imcode.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

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
