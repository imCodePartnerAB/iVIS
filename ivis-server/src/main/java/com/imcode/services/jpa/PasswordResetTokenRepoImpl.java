package com.imcode.services.jpa;

import com.imcode.entities.PasswordResetToken;
import com.imcode.repositories.PasswordResetTokenRepository;
import com.imcode.services.AbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 22.07.16.
 */
@Service
public class PasswordResetTokenRepoImpl extends AbstractService<PasswordResetToken, Long, PasswordResetTokenRepository> {
}
