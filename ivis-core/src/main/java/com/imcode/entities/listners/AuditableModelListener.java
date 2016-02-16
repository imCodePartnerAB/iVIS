package com.imcode.entities.listners;

import com.imcode.entities.interfaces.JpaAuditableEntity;
import com.imcode.entities.LogEvent.Action;
import com.imcode.entities.observer.ObserverRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

import static com.imcode.entities.observer.LogUtils.*;


/**
 * This class has all of the hooks for auditing
 *
 * @author Gonto
 * @since Dec 15, 2012
 */
public class AuditableModelListener{
    private static Logger logger = LoggerFactory.getLogger(AuditableModelListener.class);

    /**
     * After we load the Model into memory, we transform the object to a {@link Map}
     * of PropertyName => Object
     * I save this map in a transient property at the AuditableModel.
     * Then, when we're going to update this object, I'm going to check for the map of loaded values
     * and compare this to the map of the modified object. From that, I'm going to get all of the changes
     */
    @PostLoad
    public void postLoad(JpaAuditableEntity model) {
        model.setLoadedValues(getValues(model));
    }

    /**
     * Let's log a Model Removal
     */
    @PostRemove
    public void preRemove(JpaAuditableEntity model) {
//        Logger.debug("PreRemove %s", model.getClass().getSimpleName());
        logger.debug("PreRemove %s", model.getClass().getSimpleName());
        ObserverRegistry.notifyChange(model, Action.DELETE, null, model, null);
    }

    /**
     * Let's log a model creation
     */
    @PostPersist
    public void prePersist(JpaAuditableEntity model) {
//        Logger.debug("PrePersist %s", model.getClass().getSimpleName());
        logger.debug("PrePersist %s", model.getClass().getSimpleName());
        ObserverRegistry.notifyChange(model, Action.CREATE, null, null, null);
    }

    /**
     * Here we compare the loaded values vs modified values and emit logging for references
     */
    @PreUpdate
    public void preUpdate(JpaAuditableEntity model) {
//        Logger.debug("PreUpdate %s", model.getClass().getSimpleName());
        logger.debug("PreUpdate %s", model.getClass().getSimpleName());
        Map<String, Object> newValues = getValues(model);
        Map<String, Object> oldValues = model.getLoadedValues();
        if (oldValues == null) {
//            Logger.warn("The old values for model %s are null", model.toString());
            logger.warn("The old values for model %s are null", model.toString());
            return;
        }
        for (Difference difference : getDifferences(oldValues, newValues)) {
            ObserverRegistry.notifyChange(model, Action.MODIFY, difference.field, difference.oldValue,
                    difference.newValue);
        }

    }


}
