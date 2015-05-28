package com.imcode.repositories.oauth2;


import com.imcode.entities.oauth2.AppTablePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTablePermissionRepository extends JpaRepository<AppTablePermission, Long> {
}
