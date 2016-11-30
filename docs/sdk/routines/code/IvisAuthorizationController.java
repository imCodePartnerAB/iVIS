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
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@Controller
public class IvisAuthorizationController {

    @Value("#{'${client-address}' + '${redirect-relate-uri}'}")
    private String redirectUri;

    @Value("${refresh-token-validity-seconds")
    private Integer refreshTokenValiditySeconds;

    @Value("#{'${api-server-address}' + '${ivis-logout-relate-uri}'}")
    private String ivisLogoutUrl;

    @Value("${client-address}")
    private String clientAddress;

    private final AuthorizationCodeResourceDetails client;

    @Autowired
    public IvisAuthorizationController(AuthorizationCodeResourceDetails client) {
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
                                                   HttpServletResponse response,
                                                   @RequestParam("code") String code) throws UnsupportedEncodingException {
        //send post request and receive token
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(client, code, redirectUri);
        IvisOAuth2Utils.setAccessToken(request, accessToken);
        IvisOAuth2Utils.setRefreshTokenAsCokie(response, accessToken.getRefreshToken(), refreshTokenValiditySeconds);
        view.setViewName("start_page_view");//view name of start page
        return view;
    }

    //Only need redirect to this handler if IvisOAuth2Utils.isTokenGood(request) -> false
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ModelAndView unauthorizedUsers(ModelAndView view,
                                          HttpServletRequest request,
                                          @CookieValue("refreshToken") String refreshTokenCookie) throws UnsupportedEncodingException, URISyntaxException {
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(client, refreshTokenCookie);
        //logout client
        if (accessToken == null) {
            String redirectUrl = new URIBuilder(ivisLogoutUrl)
                    .addParameter("redirect_url", clientAddress + "start_page_view")//view name of start page
                    .build()
                    .toString();
            view.setViewName("redirect:" + redirectUrl);
            return view;
        }

        IvisOAuth2Utils.setAccessToken(request, accessToken);
        view.setViewName("start_page_view");
        return view;
    }

}
