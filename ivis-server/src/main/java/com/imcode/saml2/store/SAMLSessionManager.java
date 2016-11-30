package com.imcode.saml2.store;

import com.imcode.entities.Role;
import com.imcode.entities.User;
//import com.imcode.imcms.api.ContentManagementSystem;
//import com.imcode.imcms.api.User;
//import imcode.server.Imcms;
//import imcode.server.user.RoleId;
//import imcode.server.user.UserAlreadyExistsException;
//import imcode.server.user.UserDomainObject;
//import imcode.util.Utility;
import com.imcode.repositories.RoleRepository;
import com.imcode.repositories.UserRepository;
import org.apache.commons.lang.StringUtils;
import org.opensaml.common.SAMLObject;
import org.opensaml.common.binding.SAMLMessageContext;
import org.opensaml.saml2.core.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.server.UID;
import java.util.*;

/**
 * Created by Shadowgun on 20.11.2014.
 */
public class SAMLSessionManager {
    private static String SAML_SESSION_INFO = "SAML_SESSION_INFO";
    private static SAMLSessionManager instance = new SAMLSessionManager();
    private static final String SESSION_ATTRIBUTE__NEXT_URL = "next_url";

    /*public static final String REQUEST_PARAMETER__NEXT_URL = SESSION_ATTRIBUTE__NEXT_URL;
    public static final String REQUEST_PARAMETER__NEXT_META = "next_meta";
    private static final String SESSION_ATTRIBUTE__NEXT_META = "next_meta";
    private static final String SESSION_ATTRIBUTE__LOGIN_TARGET = "login.target";
    public static final String REQUEST_PARAMETER__EDIT_USER = "edit_user";
    public static final String REQUEST_PARAMETER__USERNAME = "name";
    public static final String REQUEST_PARAMETER__PASSWORD = "passwd";
    public static final String REQUEST_ATTRIBUTE__ERROR = "error";*/
    private SAMLSessionManager() {
    }

    public static SAMLSessionManager getInstance() {
        return instance;
    }

    public void createSAMLSession(HttpServletRequest request, HttpServletResponse response, SAMLMessageContext<Response,
            SAMLObject, NameID> samlMessageContext) {
        List<Assertion> assertions =
                samlMessageContext.getInboundSAMLMessage().getAssertions();
        NameID nameId = (assertions.size() != 0 && assertions.get(0).getSubject() != null) ?
                assertions.get(0).getSubject().getNameID() : null;
        String nameValue = nameId == null ? null : nameId.getValue();
        SAMLSessionInfo samlSessionInfo = new SAMLSessionInfo(nameValue,
                getAttributesMap(getSAMLAttributes(assertions)),
                getSAMLSessionValidTo(assertions));
        request.getSession().setAttribute(SAML_SESSION_INFO, samlSessionInfo);
        loginUser(samlSessionInfo, request, response);
    }

    public void loginUser(SAMLSessionInfo samlSessionInfo, HttpServletRequest request, HttpServletResponse response) {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
        UserRepository userRepository = context.getBean(UserRepository.class);
        Map<String, String> attributes = samlSessionInfo.getAttributes();
        String samlUserId = attributes.get("Subject_SerialNumber");
        User user = userRepository.findBySaml2Id(samlUserId);
        if (user == null) {
        //Create new user
            RoleRepository roleRepository = context.getBean(RoleRepository.class);
            Role roleUser = roleRepository.findFirstByName("ROLE_USER");
            user = new User(samlUserId, UUID.randomUUID().toString(), true, Collections.singleton(roleUser));
            user.setSaml2Id(samlUserId);
            user = userRepository.save(user);
        }

        //Authorize this user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        try {
            successHandler.onAuthenticationSuccess(request, response, authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        SecurityContextHolder.getContext().setAuthentication(authentication);


//        user.setActive(true);
//        user.setCompany(attributes.get("Subject_OrganisationName"));
//        user.setSessionId(attributes.get("CertificateSerialNumber"));
//        user.setCountry(attributes.get("Subject_CountryName"));
//        user.setFirstName(attributes.get("Subject_GivenName"));
//        user.setLastName(attributes.get("Subject_Surname"));
//        user.setLoginName(attributes.get("Subject_SerialNumber"));
//        UserDomainObject user = prepareUser(samlSessionInfo);
//        UserDomainObject dbUser = Imcms.getServices().getImcmsAuthenticatorAndUserAndRoleMapper().getUser(user.getLoginName());
//        if (dbUser != null) {
//            for (RoleId role : dbUser.getRoleIds())
//                user.addRoleId(role);
//            user.setId(dbUser.getId());
//        } else
//            try {
//                user.addRoleId(RoleId.CGIUSER);
//                Imcms.getServices().getImcmsAuthenticatorAndUserAndRoleMapper().addUser(user);
//            } catch (UserAlreadyExistsException e) {
//                e.printStackTrace();
//            }
//
//        ContentManagementSystem cms = Utility.initRequestWithApi(request, user);
//        if (Imcms.getServices().getConfig().isDenyMultipleUserLogin()) {
//            User currentUser = cms.getCurrentUser();
//            currentUser.setSessionId(request.getSession().getId());
//            cms.getUserService().updateUserSession(currentUser);
//        }
//        String rememberCd = user.getRememberCd();
//        if (StringUtils.isEmpty(rememberCd)) {
//            cms.getUserService().updateUserRememberCd(user);
//        }
//        Utility.setRememberCdCookie(request, response, user.getRememberCd());
//
//        Utility.makeUserLoggedIn(request, user);
//
//        try {
//            response.sendRedirect("StartDoc");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

//    private User prepareUser(SAMLSessionInfo sessionInfo) {return null;}

//    private UserDomainObject prepareUser(SAMLSessionInfo sessionInfo) {
//        UserDomainObject user = new UserDomainObject();
//        Map<String, String> attributes = sessionInfo.getAttributes();
//        user.setActive(true);
//        user.setCompany(attributes.get("Subject_OrganisationName"));
//        user.setSessionId(attributes.get("CertificateSerialNumber"));
//        user.setCountry(attributes.get("Subject_CountryName"));
//        user.setFirstName(attributes.get("Subject_GivenName"));
//        user.setLastName(attributes.get("Subject_Surname"));
//        user.setLoginName(attributes.get("Subject_SerialNumber"));
//        user.setImcmsExternal(true);
//        user.isImcmsExternal();
//        user.setLanguageIso639_2("swe");
//        return user;
//    }

    public boolean isSAMLSessionValid(HttpSession session) {
        SAMLSessionInfo samlSessionInfo = (SAMLSessionInfo)
                session.getAttribute(SAML_SESSION_INFO);
        return samlSessionInfo != null && (samlSessionInfo.getValidTo() == null || new
                Date().before(samlSessionInfo.getValidTo()));
    }

    public SAMLSessionInfo getSAMLSession(HttpSession session) {
        return (SAMLSessionInfo)
                session.getAttribute(SAML_SESSION_INFO);
    }

    public void destroySAMLSession(HttpSession session) {
        session.removeAttribute(SAML_SESSION_INFO);
    }

    public List<Attribute> getSAMLAttributes(List<Assertion> assertions) {
        List<Attribute> attributes = new ArrayList<Attribute>();
        if (assertions != null) {
            for (Assertion assertion : assertions) {
                for (AttributeStatement attributeStatement :
                        assertion.getAttributeStatements()) {
                    for (Attribute attribute : attributeStatement.getAttributes()) {
                        attributes.add(attribute);
                    }
                }
            }
        }
        return attributes;
    }

    public Date getSAMLSessionValidTo(List<Assertion> assertions) {
        org.joda.time.DateTime sessionNotOnOrAfter = null;
        if (assertions != null) {
            for (Assertion assertion : assertions) {
                for (AuthnStatement statement : assertion.getAuthnStatements()) {
                    sessionNotOnOrAfter = statement.getSessionNotOnOrAfter();
                }
            }
        }

        return sessionNotOnOrAfter != null ?
                sessionNotOnOrAfter.toCalendar(Locale.getDefault()).getTime() : null;
    }

    public Map<String, String> getAttributesMap(List<Attribute> attributes) {
        Map<String, String> result = new HashMap<String, String>();
        for (Attribute attribute : attributes) {
            result.put(attribute.getName(), attribute.getDOM().getTextContent());
        }
        return result;
    }
}
