package com.imcode.entities.interfaces;

import java.io.Serializable;

/**
 * Created by vitaly on 13.05.15.
 */
public interface JpaHierarchicalEntity<ID extends Serializable> extends JpaEntity<ID>{

    JpaHierarchicalEntity<ID> getParent();

    void setParent(JpaHierarchicalEntity<ID> parent);

}
