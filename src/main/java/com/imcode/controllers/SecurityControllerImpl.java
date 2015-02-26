package com.imcode.controllers;

import com.imcode.entities.User;
import com.imcode.exceptions.ApplicationNotFoundException;
import com.imcode.exceptions.AutorizationException;
import com.imcode.exceptions.RestException;
import com.imcode.exceptions.UserNotfoundException;
//import com.imcode.misc.errors.*;
import com.imcode.misc.ErrorResponse;
import com.imcode.misc.NormalResponse;
import com.imcode.misc.errors.ErrorFactory;
import com.imcode.misc.SecurityService;
import com.imcode.misc.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
@Controller
@DependsOn("securityService")
public class SecurityControllerImpl {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private ErrorFactory errorFactory;

    @RequestMapping("/autorisation")
    @ResponseBody
    Object getAccessToken(@RequestParam("login") String login, 
                          @RequestParam("pwd") String pwd, 
                          @RequestParam("app") long appId, WebRequest webRequest, Model model) {
        UUID uuid = null;
        try {
            uuid = securityService.createToken(login, pwd, appId);
        } catch (ApplicationNotFoundException e) {
            com.imcode.misc.errors.Error error = errorFactory.getErrorWithDescription(101);
//            error.setrequestParams(this.);
            return new ErrorResponse(error);
        } catch (UserNotfoundException e) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(5));
        } catch (AutorizationException e) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(5));
        } catch (RestException e) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(1));
        }

        TokenInfo tokenInfo = securityService.getTokenInfo(uuid);
        
        Map<String, Object> result = new HashMap<>();
        result.put("access_token", uuid);
        result.put("refresh_token", tokenInfo.getRefreshToken());
        result.put("lifetime", tokenInfo.getLifeTime());

        return new NormalResponse(result);
    }

    @RequestMapping(value = "/autorisation/info", params = "access_token")
    @ResponseBody
    Object getTokenInfo(@RequestParam("access_token") String tokenName) {
        TokenInfo tokenInfo = securityService.getTokenInfo(tokenName);
        if (tokenInfo == null) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(30));
        }
        
        return new NormalResponse(tokenInfo);
    }

    @RequestMapping(value = "/autorisation/refresh")
    @ResponseBody
    Object refreshToken(@RequestParam("access_token") String tokenName, 
                        @RequestParam("refresh_token") String refreshTokenName, WebRequest webRequest) {
        TokenInfo tokenInfo = securityService.getTokenInfo(tokenName);
        
        if (tokenInfo == null) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(30));
        }
        try {
            UUID refreshToken = UUID.fromString(refreshTokenName);
        } catch (Exception e) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(31));
        }
        
        if (!tokenInfo.getRefreshToken().equals(UUID.fromString(refreshTokenName))) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(31));
        }

        User user = tokenInfo.getUser();
        UUID newToken = null;
        
        try {
            newToken = securityService.createToken(user.getLogin(), user.getPwd(), tokenInfo.getApplication().getId());
            securityService.removeToken(tokenInfo.getId());
            tokenInfo = securityService.getTokenInfo(newToken);

            Map<String, Object> result = new HashMap<>();
            result.put("access_token", tokenInfo.getId());
            result.put("refresh_token", tokenInfo.getRefreshToken());
            result.put("lifetime", tokenInfo.getLifeTime());

            return new NormalResponse(result);
        } catch (RestException e) {
            return new ErrorResponse(errorFactory.getErrorWithDescription(1)); 
        }

    }
    //Getters & Setters
    //------------------------------------------------------------------------------------------------------------------
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public ErrorFactory getErrorFactory() {
        return errorFactory;
    }

    public void setErrorFactory(ErrorFactory errorFactory) {
        this.errorFactory = errorFactory;
    }
}
