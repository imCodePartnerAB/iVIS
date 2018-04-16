package com.imcode.repositories;


import com.imcode.entities.SchoolClass;
import com.imcode.services.NamedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>, NamedService<SchoolClass>, JpaSpecificationExecutor<SchoolClass> {

    @Modifying
    @Query("UPDATE SchoolClass sc SET sc.schoolCloudEnabled = ?2 WHERE sc.id = ?1")
    void updateSchoolCloudEnabling(Long id, boolean nextCloudEnabled);
}
