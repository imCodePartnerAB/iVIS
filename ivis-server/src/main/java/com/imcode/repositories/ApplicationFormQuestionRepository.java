package com.imcode.repositories;


import com.imcode.entities.ApplicationFormQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationFormQuestionRepository extends JpaRepository<ApplicationFormQuestion, Long>, JpaSpecificationExecutor<ApplicationFormQuestion> {
}
