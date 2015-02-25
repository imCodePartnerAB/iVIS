package com.imcode.repositories;


import com.imcode.entities.AppTableKey;
import com.imcode.entities.AppTablePermission;
import com.imcode.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTablePermissionRepository extends JpaRepository<AppTablePermission, Long> {
}
