package com.imcode.repositories;


import com.imcode.entities.ApplicationFormQuestionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationFormQuestionGroupRepository extends JpaRepository<ApplicationFormQuestionGroup, Long>, JpaSpecificationExecutor<ApplicationFormQuestionGroup> {
}
