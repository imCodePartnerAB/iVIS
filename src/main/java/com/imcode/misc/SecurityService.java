package com.imcode.misc;

import com.imcode.exceptions.RestException;

import java.util.UUID;

/**
 * Created by vitaly on 25.02.15.
 */
public interface SecurityService {
    public UUID createToken(String userName, String pwd, Long applicationId) throws RestException;

    public TokenInfo getTokenInfo(UUID token);

    public TokenInfo getTokenInfo(String tokenName);

    public void removeToken(UUID token);
    
    public void removeToken(String tokenName);
}

