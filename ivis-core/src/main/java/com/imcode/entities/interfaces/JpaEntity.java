package com.imcode.entities.interfaces;

import com.imcode.entities.ApplicationFormQuestion;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;

/**
 * Created by vitaly on 10.07.15.
 */
public interface JpaEntity<ID extends Serializable> extends Serializable {
    Comparator<JpaEntity<Long>> BY_ID_COMPARATOR = nullsLast(comparing(JpaEntity::getId, nullsLast(Long::compare)));

    ID getId();

    void setId(ID id);

    String getClassDescription();

    boolean deepEquals(JpaEntity entity);

    static <T extends JpaEntity> boolean deepEquals(T a, T b) {
        return ((a == b) || (a != null && a.deepEquals(b)));
    }

    static <T extends Collection<? extends JpaEntity>> boolean deepEquals(T a, T b) {
        if (a == b) {
            return true;
        } else if (a == null || b == null || a.size() != b.size()) {
            return false;
        } else{
            Iterator<? extends JpaEntity> aIterator = a.iterator();
            Iterator<? extends JpaEntity> bIterator = b.iterator();

            while (aIterator.hasNext() && bIterator.hasNext()) {
                JpaEntity o1 = aIterator.next();
                JpaEntity o2 = bIterator.next();
                if (!deepEquals(o1, o2))
                    return false;
            }

            return !(aIterator.hasNext() || bIterator.hasNext());
        }
    }
}
