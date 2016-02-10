package com.imcode.entities.observer;

import com.imcode.entities.observer.Event.Action;

import java.util.HashSet;
import java.util.Set;

/**
 * This is the registry for all of the observers
 *
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
public class ObserverRegistry {

    private static Set<Observer<Object>> observers = new HashSet<>();

    public static void notifyChange(Object model, Action action, String field, Object previousValue,
            Object newValue) {
        for (Observer<Object> observer : observers) {
            if (observer.handles(model.getClass())) {
                observer.notifyChange(model, action, field, previousValue, newValue);
            }
        }

    }

    public static void register(Observer<?> observer) {
        observers.add(observer);
    }

    public static void deregister(Observer<?> observer) {
        observers.remove(observer);
    }

}
