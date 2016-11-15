package com.imcode.repositories;

import com.imcode.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by ruslan on 15.11.16.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    @Modifying(clearAutomatically = true)
    @Query("update Permission p set p.updated = false ")
    void makeAllUnUpdated();

    @Modifying(clearAutomatically = true)
    @Query("update Permission p set p.updated = true where p.hash = :hash")
    void setUpdated(@Param("hash") Integer hash);
}
