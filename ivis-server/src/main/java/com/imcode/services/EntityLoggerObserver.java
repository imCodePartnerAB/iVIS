package com.imcode.services;

import com.imcode.entities.Application;
import com.imcode.entities.LogEvent;
import com.imcode.entities.User;
import com.imcode.entities.interfaces.JpaAuditableEntity;
import com.imcode.entities.observer.LogEventObserver;
import com.imcode.entities.observer.ObserverRegistry;
import com.imcode.entities.superclasses.AbstractIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Future;

/**
 * This observer logs changes to a certain model
 *
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
@Service
public class EntityLoggerObserver implements LogEventObserver<AbstractIdEntity<Long>> {
    @Autowired
    private LogEventService eventService;


    @PostConstruct
    public void postConstruct() {
        ObserverRegistry.register(this);
    }

    @Override
    public boolean handles(Class<?> clazz) {
        return Application.class.isAssignableFrom(clazz);
    }

    @Override
    public void notifyChange(AbstractIdEntity<Long> model, LogEvent.Action action, String field, Object previousValue, Object newValue) {

        //Mock current user getting
        //You would get this by using Security.connectedUser() or something like that
        User user = getCurrentUser();

        //Mock account geting
        LogEvent event = new LogEvent(model, action, field, getAsString(previousValue), getAsString(newValue), user);

        //We need to save the Event to the database in another thread
        //As otherwise, this will be a StackoverflowEx
        eventService.saveAsync(event);
    }

    public static User getCurrentUser() {
        User user = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof User) {
            user = (User) principal;
        }

        return user;
    }

    public static String getAsString(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (Iterable.class.isAssignableFrom(obj.getClass())) {
            return toStringIterable((Iterable) obj);
        } else {
            return obj.toString();
        }
    }

    private static String compactToString(AbstractIdEntity<?> model) {
        return model.getClass().getSimpleName() + "[" + model.getId() + "]";
    }

    private static String toStringIterable(Iterable iterable) {
        StringBuilder builder = new StringBuilder("Iterable(");
        boolean hasElements = false;
        for(Object element : iterable ) {
            hasElements = true;
            if (AbstractIdEntity.class.isAssignableFrom(element.getClass())) {
                builder.append(compactToString((AbstractIdEntity<?>) element));
            } else {
                builder.append(element.toString());
            }
            builder.append(", ");
        }
        if (hasElements) {
            builder.delete(builder.length() - 3, builder.length() - 1);
        }
        builder.append(")");
        return builder.toString();
    }
}
