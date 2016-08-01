package com.imcode.services.jpa;

import com.imcode.entities.ServiceMethod;
import com.imcode.repositories.ServiceMethodRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.ServiceMethodService;
import org.springframework.stereotype.Service;

/**
 * Created by ruslan on 01.08.16.
 */
@Service
public class ServiceMethodRepoImpl extends AbstractService<ServiceMethod, Long, ServiceMethodRepository> implements ServiceMethodService{
}
