package imcode.services.filter;

import imcode.services.utils.IvisOAuth2Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.http.AccessTokenRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

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
        try {
            servletRequest = (HttpServletRequest) request;
        } catch (ClassCastException e) {
            return;
        }

        logger.info("Access to protected resources.");
        if (!IvisOAuth2Utils.isTokenGood(servletRequest)) {
            throw new UnauthorizedUserException("Token isn't good.");
        } else if (Objects.nonNull(roles)
                && !IvisOAuth2Utils.getIvisLoggedInUser(servletRequest).hasRoles(roles.split(","))){
            throw new AccessDeniedException("Token is good, but roles aren't.");
        }
        logger.info("Access is permitted");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
