package com.imcode.services.jpa;

import com.imcode.entities.Email;
import com.imcode.repositories.EmailRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceRepoImpl extends AbstractService<Email, Long, EmailRepository> implements EmailService {
}
