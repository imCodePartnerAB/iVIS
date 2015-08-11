package com.imcode.services.jpa;

import com.imcode.entities.SchoolTransport;
import com.imcode.repositories.SchoolRepository;
import com.imcode.repositories.SchoolTransportRepository;
import com.imcode.services.AbstractNamedEntityService;
import com.imcode.services.AbstractService;
import com.imcode.services.SchoolService;
import com.imcode.services.SchoolTransportService;
import org.springframework.stereotype.Service;

@Service
public class SchoolTransportServiceRepoImpl extends AbstractNamedEntityService<SchoolTransport, Long, SchoolTransportRepository> implements SchoolTransportService {
}
