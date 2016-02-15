package com.imcode.entities.observer;

import com.imcode.entities.LogEvent.Action;

/**
 * An observer
 *
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
public interface LogEventObserver<T> {

    /**
     * Wether this observer observes this class
     */
    boolean handles(Class<?> clazz);

    /**
     * Notifies a change
     */
    void notifyChange(T model, Action action, String field, Object previousValue, Object newValue);

}
