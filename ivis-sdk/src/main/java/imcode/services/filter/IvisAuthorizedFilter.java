package imcode.services.filter;

import imcode.services.utils.IvisOAuth2Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ruslan on 01.11.16.
 */
public class IvisAuthorizedFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(IvisAuthorizedFilter.class);

    private String roles;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.roles = filterConfig.getInitParameter("roles");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest;
        HttpServletResponse servletResponse;
        try {
            servletRequest = (HttpServletRequest) request;
            servletResponse = (HttpServletResponse) response;
        } catch (ClassCastException e) {
            return;
        }

        logger.debug("Access to protected resources.");
        if (!IvisOAuth2Utils.isTokenGood(servletRequest)) { // if token no good than skip
            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            logger.info("Token no good.");
        } else if (roles == null){ // if token good, but you don't need use roles based
            servletResponse.setStatus(servletResponse.getStatus());
            logger.info("Token good not need use roles.");
        } else if (IvisOAuth2Utils.getIvisLoggedInUser(servletRequest).hasRoles(roles.split(","))){ // if you need use roles based
            logger.info("Token and roles good.");
            servletResponse.setStatus(servletResponse.getStatus());
        } else {
            logger.info("Token good, but roles aren't.");
            servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
