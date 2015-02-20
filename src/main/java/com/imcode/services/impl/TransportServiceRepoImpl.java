package com.imcode.services.impl;

import com.imcode.entities.Transport;
import com.imcode.repositories.TransportRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.TransportService;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceRepoImpl extends AbstractService<Transport, Long, TransportRepository> implements TransportService{
}
