package com.imcode.misc;

import com.imcode.entities.Application;
import com.imcode.entities.User;
import com.imcode.exceptions.ApplicationNotFoundException;
import com.imcode.exceptions.AutorizationException;
import com.imcode.exceptions.RestException;
import com.imcode.exceptions.UserNotfoundException;
import com.imcode.services.ApplicationService;
import com.imcode.services.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
public class SecurityServiceImpl implements SecurityService {
    private static final long DEFAULT_TOKEN_LIFETIME = 60 * 60 * 24L;
    private long tokenLifetime = DEFAULT_TOKEN_LIFETIME;
    private Map<UUID, TokenInfo> tokenInfoMap = new HashMap<>();
    private ApplicationService applicationService;
    private UserService userService;

    public UUID createToken(String userName, String pwd, Long applicationId) throws RestException{
        UUID uuid = null;
        User user = userService.getBylogin(userName);
        Application app = applicationService.find(applicationId);

        if (user == null) {
            throw new UserNotfoundException();
        }else if (app == null) {
            throw new ApplicationNotFoundException();
        }else if (!user.getPwd().equals(pwd)) {
            throw new AutorizationException();
        }
        
//        if (user != null && app != null && ) {
            TokenInfo tokenInfo = new TokenInfo(user, app, tokenLifetime);
            uuid = tokenInfo.getId();
            synchronized (this) {
                tokenInfoMap.put(uuid, tokenInfo);
            }
//        }

        return uuid;
    }

    public TokenInfo getTokenInfo(UUID token) {
        TokenInfo tokenInfo = tokenInfoMap.get(token);

        if (tokenInfo != null && tokenInfo.isRotten()) {
            synchronized (this) {
                tokenInfoMap.remove(tokenInfo);
            }
            tokenInfo = null;
        }

        return tokenInfo;
    }

    public TokenInfo getTokenInfo(String tokenName) {
        UUID token = UUID.fromString(tokenName.trim());
        return getTokenInfo(token);
    }

    @Override
    public synchronized void removeToken(UUID token) {
        tokenInfoMap.remove(token);
    }
    public void removeToken(String tokenName) {
        removeToken(UUID.fromString(tokenName));
    }

    public ApplicationService getApplicationService() {
        return applicationService;
    }

    public void setApplicationService(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


