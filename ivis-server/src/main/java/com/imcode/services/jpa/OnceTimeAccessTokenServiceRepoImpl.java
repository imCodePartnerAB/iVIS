package com.imcode.services.jpa;

import com.imcode.entities.OnceTimeAccessToken;
import com.imcode.repositories.OnceTimeAccessTokenRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.OnceTimeAccessTokenService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 25.07.16.
 */
@Service
public class OnceTimeAccessTokenServiceRepoImpl extends AbstractService<OnceTimeAccessToken, Long, OnceTimeAccessTokenRepository> implements OnceTimeAccessTokenService {
}
