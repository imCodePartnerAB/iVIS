package com.imcode.services.jpa;

import com.imcode.entities.TypedAccessToken;
import com.imcode.repositories.TypedAccessTokenRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.TypedAccessTokenService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 25.07.16.
 */
@Service
public class TypedAccessTokenRepoImpl extends AbstractService<TypedAccessToken, Long, TypedAccessTokenRepository> implements TypedAccessTokenService {
}
