package com.imcode.services.jpa;

import com.imcode.entities.VerificationToken;
import com.imcode.repositories.VerificationTokenRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.VerificationTokenService;

/**
 * Created by ruslan on 25.07.16.
 */
public class VerificationTokenRepoImpl extends AbstractService<VerificationToken, Long, VerificationTokenRepository> implements VerificationTokenService {
}
