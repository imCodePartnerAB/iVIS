package com.imcode.repositories;

import com.imcode.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly on 13.05.15.
 */
public interface AddressRepository extends JpaRepository<Address, Long>{
}
