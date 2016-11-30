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

    List<Permission> findByUpdatedFalse();

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM dbo_role_permission_cross WHERE permission_id = ?1 ", nativeQuery = true)
    void deleteAssociation(Long permissionId);

    @Query("select case when p is not null then true else false end " +
            "from Permission p, Role rC, Role rU, JpaClientDetails c, User u " +
            "where " +
            "rC member of c.authorities and " +
            "rU member of u.roles and " +
            "p member of rU.permissions and " +
            "p member of rC.permissions and " +
            "c.clientId = :clientId and " +
            "u.id = :userId and " +
            "p.hash = :hash")
    Boolean getPermission(@Param("clientId") String clientId, @Param("userId") Long userId, @Param("hash") Integer hash);
}
