package com.imcode.services.impl;

import com.imcode.entities.TransportType;
import com.imcode.repositories.TransportTypeRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.TransportTypeService;
import org.springframework.stereotype.Service;

@Service
public class TransportTypeServiceRepoImpl extends AbstractService<TransportType, Long, TransportTypeRepository> implements TransportTypeService{
}
