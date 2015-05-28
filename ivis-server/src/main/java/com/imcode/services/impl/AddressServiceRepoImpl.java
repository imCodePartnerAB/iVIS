package com.imcode.services.impl;

import com.imcode.entities.Address;
import com.imcode.entities.School;
import com.imcode.repositories.AddressRepository;
import com.imcode.repositories.SchoolRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AddressService;
import com.imcode.services.SchoolService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceRepoImpl extends AbstractService<Address, Long, AddressRepository> implements AddressService {
}
