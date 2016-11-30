package com.imcode.entities.observer;

import com.imcode.entities.LogEvent.Action;

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
    private static Set<LogEventObserver<?>> observers = new HashSet<>();

    public static <T> void notifyChange(T model, Action action, String field, Object previousValue,
                                                                Object newValue) {
        for (LogEventObserver<?> o : observers) {
            @SuppressWarnings("unchecked")
            LogEventObserver<T> observer = (LogEventObserver<T>) o;
            if (observer.handles(model.getClass())) {
                observer.notifyChange(model, action, field, previousValue, newValue);
            }
        }

    }

    public static void register(LogEventObserver<?> observer) {
        observers.add(observer);
    }

    public static void deregister(LogEventObserver<?> observer) {
        observers.remove(observer);
    }

    public static void asinc(Runnable work) {

    }

//    private abstract class AbstractWorker implements Runnable {
//
//        @Override
//        public void run() {
//            try {
//                while (!Thread.interrupted()) {
//                    doWork();
//                }
//            } catch (InterruptedException e) {
//                interrupting();
//            }finally {
//                finalizing();
//            }
//
//        }
//
//        protected void finalizing() {
//            System.out.println(getClass().getSimpleName() + " ended.");
//        }
//
//        protected void interrupting() {
//            System.out.println(getClass().getSimpleName() + " interrupted.");
//        }
//
//        public abstract void doWork() throws InterruptedException;
//
//    }

}
