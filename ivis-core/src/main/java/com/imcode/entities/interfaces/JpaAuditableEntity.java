package com.imcode.entities.interfaces;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.util.Map;

/**
 * This is the base class for every model that is going to be observed and audited
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
//@MappedSuperclass
//@EntityListeners(AuditableModelListener.class)
public interface JpaAuditableEntity {
    Map<String, Object> getLoadedValues();

    void setLoadedValues(Map<String, Object> loadedValues);
}
