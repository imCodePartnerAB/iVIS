package com.imcode.services.jpa;

import com.imcode.entities.SchoolTransport;
import com.imcode.repositories.SchoolTransportRepository;
import com.imcode.services.AbstractNamedService;
import com.imcode.services.SchoolTransportService;
import org.springframework.stereotype.Service;

@Service
public class SchoolTransportServiceRepoImpl extends AbstractNamedService<SchoolTransport, Long, SchoolTransportRepository> implements SchoolTransportService {
}
