package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "dbo_school_transport")
public class SchoolTransport extends AbstractNamedEntity<Long> implements Serializable {
    public SchoolTransport() {
    }

    public SchoolTransport(String name) {
        super(name);
    }

    public SchoolTransport(Long aLong, String name) {
        super(aLong, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
