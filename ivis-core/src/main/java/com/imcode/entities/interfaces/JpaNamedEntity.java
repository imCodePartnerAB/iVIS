package com.imcode.entities.interfaces;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
public interface JpaNamedEntity<ID extends Serializable> extends JpaEntity<ID>{

    public String getName();

    public void setName(String name);

}
