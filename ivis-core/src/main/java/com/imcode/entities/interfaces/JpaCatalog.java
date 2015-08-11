package com.imcode.entities.interfaces;

import java.io.Serializable;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaCatalog<ID extends Serializable> extends JpaNamedEntity<ID>, JpaHierarchicalEntity<ID> {
}
