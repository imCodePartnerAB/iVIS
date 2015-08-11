package com.imcode.entities.interfaces;

import java.io.Serializable;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaEntity<ID extends Serializable> {
    ID getId();

    void setId(ID id);

    String getClassDescription();
//    boolean isDeleted();
//
//    boolean setDeleted();
}
