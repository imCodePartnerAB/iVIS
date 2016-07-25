package com.imcode.utils;

import com.imcode.entities.TypedAccessToken;
import com.imcode.entities.User;
import com.imcode.services.UserService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.WebRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vitaly on 09.12.15.
 */
public class StaticUtls {

    public static void nullAwareBeanCopy(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object dest, String name, Object value)
                    throws IllegalAccessException, InvocationTargetException {
                if (value instanceof Collection<?>) {
                    if (!((Collection) value).isEmpty()) {
                        super.copyProperty(dest, name, value);
                    }
                } else if (value != null) {
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

    public static boolean executeCmd(String command) {
        String[] cmd = {"/bin/bash", "-c", command};
        Process process = null;
        try {
            process = new ProcessBuilder(cmd).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return process.exitValue() == 0;
    }

    public static Process executeCmdConfig(String command, String config) {
        String[] cmd = {config, "-c", command};
        Process process = null;
        try {
            process = new ProcessBuilder(cmd).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return process;
    }

    public static void encodeUserPassword(User user) {
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        user.setPassword(encodePassword);
        user.setConfirmPassword(encodePassword);
    }

    public static void genToken(TypedAccessToken token, User user, int expiration) {
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(calculateExpiryDate(expiration));
    }

    private static Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }




}
