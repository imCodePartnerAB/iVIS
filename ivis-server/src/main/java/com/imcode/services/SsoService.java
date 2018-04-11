package com.imcode.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Single sign-on service
 */
public interface SsoService {

    void logIn(HttpServletRequest request, HttpServletResponse response);

    void logOut(HttpServletRequest request, HttpServletResponse response);
}
