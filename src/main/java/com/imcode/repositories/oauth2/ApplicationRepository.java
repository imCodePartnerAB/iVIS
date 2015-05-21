package com.imcode.repositories.oauth2;


import com.imcode.entities.oauth2.JpaClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<JpaClientDetails, Long> {
}
