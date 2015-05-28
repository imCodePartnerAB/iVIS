package com.imcode.services.impl;

import com.imcode.entities.Phone;
import com.imcode.repositories.PhoneRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.PhoneService;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceRepoImpl extends AbstractService<Phone, Long, PhoneRepository> implements PhoneService {
}
