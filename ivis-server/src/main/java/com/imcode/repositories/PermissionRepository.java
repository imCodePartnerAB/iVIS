package com.imcode.repositories;

import com.imcode.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ruslan on 15.11.16.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {

    @Override
    @Query("select p from Permission p order by p.entityName")
    List<Permission> findAll();

    @Modifying(clearAutomatically = true)
    @Query("update Permission p set p.updated = false ")
    void makeAllUnUpdated();

    @Modifying(clearAutomatically = true)
    @Query("update Permission p set p.updated = true where p.hash = :hash")
    void setUpdated(@Param("hash") Integer hash);

    @Modifying(clearAutomatically = true)
    @Query("delete from Permission p where p.updated = false ")
    void deleteUnUpdated();
}
