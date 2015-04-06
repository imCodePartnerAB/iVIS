package com.imcode.misc;

import com.imcode.entities._Application;
import com.imcode.entities.User;
import com.imcode.exceptions.ApplicationNotFoundException;
import com.imcode.exceptions.AutorizationException;
import com.imcode.exceptions.RestException;
import com.imcode.exceptions.UserNotfoundException;
import com.imcode.repositories.TokenInfoRepository;
import com.imcode.services.ApplicationService;
import com.imcode.services.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
public class SecurityServiceImpl{// implements SecurityService {
//    private static final long DEFAULT_TOKEN_LIFETIME = 60 * 60 * 24L;
//    private long tokenLifetime = DEFAULT_TOKEN_LIFETIME;
//    private Map<UUID, TokenInfo> tokenInfoMap = new HashMap<>();
//    private ApplicationService applicationService;
//    private UserService userService;
//    private TokenInfoRepository tokenInfoRepository;
//
//    public UUID createToken(String userName, String pwd, Long applicationId) throws RestException{
//        UUID uuid = null;
//        User user = userService.getBylogin(userName);
//        _Application app = applicationService.find(applicationId);
//
//        if (user == null) {
//            throw new UserNotfoundException();
//        }else if (app == null) {
//            throw new ApplicationNotFoundException();
//        }else if (!user.getPwd().equals(pwd)) {
//            throw new AutorizationException();
//        }
//
////        if (user != null && app != null && ) {
//            TokenInfo tokenInfo = new TokenInfo(user, app, tokenLifetime);
//            uuid = tokenInfo.getId();
//            synchronized (this) {
//                tokenInfoMap.put(uuid, tokenInfo);
//            }
//        tokenInfoRepository.save(tokenInfo);
////        }
//
//        return uuid;
//    }
//
//    public TokenInfo getTokenInfo(UUID token) {
//        TokenInfo tokenInfo = tokenInfoMap.get(token);
//
//        if (tokenInfo != null && tokenInfo.isRotten()) {
//            removeToken(token);
//            tokenInfo = null;
//        }
//
//        return tokenInfo;
//    }
//
//    public TokenInfo getTokenInfo(String tokenName) {
//        UUID token = UUID.fromString(tokenName.trim());
//        return getTokenInfo(token);
//    }
//
//    @Override
//    public void removeToken(UUID token) {
//        synchronized (this) {
//            tokenInfoMap.remove(token);
//        }
//
//        tokenInfoRepository.delete(token);
//    }
//
//    public void removeToken(String tokenName) {
//        removeToken(UUID.fromString(tokenName));
//    }
//
//    public void initialize() {
//        List<TokenInfo> tokenInfoList = tokenInfoRepository.findAll();
//
//        for (TokenInfo tokenInfo : tokenInfoList) {
//            if (!tokenInfo.isRotten()) {
//                tokenInfoMap.put(tokenInfo.getId(), tokenInfo);
//            } else {
//                tokenInfoRepository.delete(tokenInfo);
//            }
//        }
//    }
//
//    public ApplicationService getApplicationService() {
//        return applicationService;
//    }
//
//    public void setApplicationService(ApplicationService applicationService) {
//        this.applicationService = applicationService;
//    }
//
//    public UserService getUserService() {
//        return userService;
//    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    public TokenInfoRepository getTokenInfoRepository() {
//        return tokenInfoRepository;
//    }
//
//    public void setTokenInfoRepository(TokenInfoRepository tokenInfoRepository) {
//        this.tokenInfoRepository = tokenInfoRepository;
//    }
}


