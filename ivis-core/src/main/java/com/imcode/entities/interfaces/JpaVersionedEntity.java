package com.imcode.entities.interfaces;

import java.io.Serializable;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaVersionedEntity<T, ID extends Serializable> extends JpaEntity<ID>{
    T getVersion();

    void setVersion(T version);
}
