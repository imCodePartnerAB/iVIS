package com.imcode.entities;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.util.*;

/**
 * Created by vitaly on 14.10.15.
 */
@Entity
@Table(name = "dbo_application_form")
public class ApplicationForm extends AbstractNamedEntity<Long> {

    @Column
    private Integer version;

    //    @ElementCollection(fetch = FetchType.EAGER)
//    @OrderBy("sortOrder ASC")
//    @CollectionTable(name = "dbo_application_form_question",
//            joinColumns = @JoinColumn(name = "applicationFormId"),
//            uniqueConstraints = @UniqueConstraint(columnNames = {"applicationFormId", "xsdElementName"}))
//    @Transient
//    private Set<ApplicationFormQuestion> questions;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationForm")
    private List<ApplicationFormStep> steps = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationForm")
    private Set<Application> applications;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

//    public Set<ApplicationFormQuestion> getQuestions() {
//        return questions;
//    }
//
//    public void setQuestions(Set<ApplicationFormQuestion> questions) {
//        this.questions = questions;
//    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append('v');
        sb.append(getVersion());

        return sb.toString();
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        ApplicationForm that = (ApplicationForm) entity;

        return Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.version, that.version)
                && JpaEntity.deepEquals(this.steps, that.steps);
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    public List<ApplicationFormStep> getSteps() {
        return steps;
    }

    public void setSteps(List<ApplicationFormStep> steps) {
        this.steps = steps;
    }
}
