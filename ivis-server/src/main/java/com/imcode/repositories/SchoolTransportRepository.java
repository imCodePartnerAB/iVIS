package com.imcode.repositories;


import com.imcode.entities.SchoolTransport;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SchoolTransportRepository extends JpaRepository<SchoolTransport, Long>, NamedService<SchoolTransport>, JpaSpecificationExecutor<SchoolTransport> {
}
