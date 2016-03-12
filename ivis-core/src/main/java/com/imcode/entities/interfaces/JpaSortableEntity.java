package com.imcode.entities.interfaces;

import com.imcode.entities.superclasses.AbstractNamedEntity;
import com.imcode.entities.superclasses.AbstractSortableNamedEntity;
import org.apache.commons.lang3.ObjectUtils;

import java.io.Serializable;
import java.util.Comparator;

import static java.util.Comparator.*;
import static java.util.Comparator.comparing;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaSortableEntity<T> extends Comparable<T>, Serializable {
    Comparator<JpaSortableEntity> DEFAULT_COMPARATOR = nullsLast(comparing(JpaSortableEntity::getSortOrder, nullsLast(Integer::compare)));

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);

//    public static void main(String[] args) {
////        class Sortable extends AbstractSortableNamedEntity<Long> {}
//
//        JpaSortableEntity s1 = new AbstractSortableNamedEntity<Integer>(1, "Step 1", null){};
//        JpaSortableEntity s2 = new AbstractSortableNamedEntity<Integer>(1, "Step 2", 1){};
//        System.out.println(DEFAULT_COMPARATOR.compare(s1, s2));
//    }
}
