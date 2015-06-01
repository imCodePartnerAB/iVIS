package com.imcode.services.jpa;

import com.imcode.entities.Address;
import com.imcode.repositories.AddressRepository;
import com.imcode.services.AbstractService;
import com.imcode.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceRepoImpl extends AbstractService<Address, Long, AddressRepository> implements AddressService {
}
