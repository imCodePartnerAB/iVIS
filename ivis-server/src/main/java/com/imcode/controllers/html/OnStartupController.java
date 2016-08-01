package com.imcode.controllers.html;

import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.entities.embed.RestMethod;
import com.imcode.services.OnceTimeAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by ruslan on 01.08.16.
 */
@Controller
public class OnStartupController {


    @Autowired
    public OnceTimeAccessTokenService onceTimeAccessTokenService;

    @PostConstruct
    public void deleteExpiredOrUsedOnceTimeAccessTokens() {
        List<OnceTimeAccessToken> onceTimeAccessTokens = onceTimeAccessTokenService.selectExpiredOrUsedTokens();
        onceTimeAccessTokenService.delete(onceTimeAccessTokens);
    }

    @PostConstruct
    public void persistAllEntityServicesAndMethods() {

    }




}
