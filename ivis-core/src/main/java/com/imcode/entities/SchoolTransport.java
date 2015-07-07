package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imcode.entities.enums.ServiceTypeEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
