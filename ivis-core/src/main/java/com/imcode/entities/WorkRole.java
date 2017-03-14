package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Entity WorkRole holds work role that owned people relate to school.
 */
@Entity
@Table(name = "dbo_work_role")
public class WorkRole extends AbstractNamedEntity<Long> implements Serializable {

    public WorkRole() {
    }

    public WorkRole(String name) {
        super(name);
    }

    public WorkRole(Long aLong, String name) {
        super(aLong, name);
    }
}
