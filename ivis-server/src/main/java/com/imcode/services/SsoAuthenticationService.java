package com.imcode.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SsoAuthenticationService {

    void authenticate(HttpServletRequest request, HttpServletResponse response);
}
