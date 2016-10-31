package com.imcode.imcms.addon.ivisclient.controllers;

import imcode.services.utils.IvisOAuth2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@Controller
public class LoginController {

    @Value("#{'${client-address}' + '${redirect-relate-uri}'}")
    private String redirectUri;

    private final AuthorizationCodeResourceDetails client;

    @Autowired
    public LoginController(AuthorizationCodeResourceDetails client) {
        this.client = client;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView view) throws URISyntaxException {
        String oAuth2AuthirizationUrl = IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, redirectUri, false);
        view.setViewName("redirect:" + oAuth2AuthirizationUrl);
        return view;
    }

    @RequestMapping(value = "${redirect-relate-uri}", method = RequestMethod.GET)
    public ModelAndView authorizationClientProcess(ModelAndView view,
                                                   HttpServletRequest request,
                                                   @RequestParam("code") String code) throws UnsupportedEncodingException {
        //send post request and receive token
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(client, code, redirectUri);
        IvisOAuth2Utils.setAccessToken(request, accessToken);
        view.setViewName("start_page_view");//view name of start page
        return view;
    }

}
