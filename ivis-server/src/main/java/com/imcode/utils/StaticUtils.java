package com.imcode.utils;

import com.imcode.entities.User;
import com.imcode.services.UserService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.WebRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by vitaly on 09.12.15.
 */
public class StaticUtils {

    public static void saveObjectToFile(Object obj, String fileName) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            stream.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static<T> T loadObjectFromFile(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));) {
            return (T) inputStream.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static GenericXmlApplicationContext getApplicationContext() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:/spring/data.xml");
        ctx.refresh();
        return ctx;
    }

    public static void nullAwareBeanCopy(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object dest, String name, Object value)
                    throws IllegalAccessException, InvocationTargetException {
                if(value != null) {
                    super.copyProperty(dest, name, value);
                }
            }
        }.copyProperties(dest, source);
    }

    public static User getCurrentUser(WebRequest webRequest, UserService userService) {
        User user = null;

        try {
            Authentication authentication = (Authentication) webRequest.getUserPrincipal();
            user = (User) authentication.getPrincipal();
            user = userService.find(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }


}
