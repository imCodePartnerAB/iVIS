package com.imcode.services;


import com.imcode.entities.OnceTimeAccessToken;

import java.util.List;

/**
 * Created by ruslan on 25.07.16.
 */
public interface OnceTimeAccessTokenService extends GenericService<OnceTimeAccessToken, Long> {

    List<OnceTimeAccessToken> selectExpiredOrUsedTokens();

}
