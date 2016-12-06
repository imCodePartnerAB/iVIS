package com.imcode.utils;

import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.User;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.UserService;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.MethodParameter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by vitaly on 09.12.15.
 */
public class StaticUtls {

    public static boolean nullAwareBeanCopy(Object dest, Object source) throws IllegalAccessException, InvocationTargetException {
        boolean [] isCopied = {false};
        new BeanUtilsBean() {
            @Override
            public void copyProperty(Object dest, String name, Object value)
                    throws IllegalAccessException, InvocationTargetException {

                boolean isCollNotEmpty = value instanceof Collection<?>
                        && !((Collection) value).isEmpty();

                boolean isMapNotEmpty = value instanceof Map<?, ?>
                        && !((Map) value).isEmpty();

                if ( isCollNotEmpty || isMapNotEmpty || Objects.nonNull(value) ) {
                    isCopied[0] = true;
                    super.copyProperty(dest, name, value);
                }
            }
        }.copyProperties(dest, source);

        return isCopied[0];
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

    public static String genLinkConfirmationForOnceTimeAccessToken(OnceTimeAccessToken token, String host, String action) {

        String uri = null;

        try {
            URIBuilder uriBuilder = new URIBuilder(host + "/"+ action + "/confirm");
            uriBuilder.addParameter("access", token.getToken());
            uriBuilder.addParameter("id", token.getId() + "");
            uri = uriBuilder.build().toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return uri;

    }

    public static String checkOnceTimeAccessToken(OnceTimeAccessToken accessToken, String access) {

        String message = null;

        if (accessToken == null) {
            message = "You haven't access rights";
        } else if (!accessToken.getToken().equals(access)) {
            message = "Your access rights is wrong";
        } else if (accessToken.isExpired()) {
            message = "Your access rights is expired";
        } else if (accessToken.getUsed()) {
            message = "Your access rights is used";
        }

        return message;
    }

    public static Boolean checkUserPasswordMatch(User user, String password) {

        String userEncodedPassword = user.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password, userEncodedPassword);

    }

    public static void checkNullAndSetNoContent(Object object, HttpServletResponse response) {

        if (object == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }

    }

    public static void rejectNullValue(Object object, String message) throws MethodArgumentNotValidException {

        if (object == null) {
            BindingResult bindingResult = new BeanPropertyBindingResult(object, "entity");
            bindingResult.reject(null, message);
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

    }

    public static Integer getHashFrom(HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        Parameter[] parameters = method.getParameters();
        Integer hash = method.hashCode() + handlerMethod.getBeanType().getTypeName().hashCode();
        for (int i = 0; i < parameters.length; i++) {
            hash += parameters[i].getType().getTypeName().hashCode() + 31 * i;
        }
        return hash;
    }



}
