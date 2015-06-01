package com.imcode.services.jpa;

import com.imcode.entities.School;
import com.imcode.entities.Statement;
import com.imcode.repositories.SchoolRepository;
import com.imcode.repositories.StatementRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.SchoolService;
import com.imcode.services.StatementService;
import org.springframework.stereotype.Service;

@Service
public class StatementServiceRepoImpl extends AbstractService<Statement, Long, StatementRepository> implements StatementService {
}
