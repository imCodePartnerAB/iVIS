package com.imcode.entities.interfaces;

import java.io.Serializable;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaEntity<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);

    String getClassDescription();

    default boolean deepEquals(JpaEntity entity) {
        return equals(entity);
    }

    static<T extends JpaEntity<?>> boolean deepEquals(T a, T b) {
        return ((a == b) || (a != null && a.deepEquals(b)));
    }
//    boolean isDeleted();
//
//    boolean setDeleted();
}
