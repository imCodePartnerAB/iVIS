package com.imcode.entities;

import com.imcode.entities.embed.ApplicationFormQuestion;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    @CollectionTable(name = "dbo_application_form_question",
            joinColumns = @JoinColumn(name = "applicationFormId"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"applicationFormId", "xsdElementName"}))
    private Set<ApplicationFormQuestion> questions;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationForm")
    private Set<Application> applications;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Set<ApplicationFormQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<ApplicationFormQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append('v');
        sb.append(getVersion());

        return sb.toString();
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (this == entity) return true;
        if (entity == null || getClass() != entity.getClass()) return false;

        ApplicationForm that = (ApplicationForm) entity;
        if (
                Objects.equals(this.id, that.id)
                        && Objects.equals(this.name, that.name)
                        && Objects.equals(this.version, that.version)) {
            if (this.questions == that.questions) {
                return true;
            } else if (this.questions != null && that.questions != null && this.questions.size() == that.questions.size()) {
                Set<ApplicationFormQuestion> thisQuestions = this.questions;
                Set<ApplicationFormQuestion> tempQuestions = new HashSet<>(that.questions);

                //deep equals question sets
                for (ApplicationFormQuestion thisQuestion : thisQuestions) {
                    Iterator<ApplicationFormQuestion> iterator = tempQuestions.iterator();
                    boolean hasEquals = false;

                    while (iterator.hasNext()) {
                        ApplicationFormQuestion thatQuestion = iterator.next();
                        //remove equal element if found
                        if (EqualsBuilder.reflectionEquals(thisQuestion, thatQuestion, false)) {
                            iterator.remove();
                            hasEquals = true;
                            break;
                        }
                    }

                    //if equality not found
                    if (!hasEquals) {
                        return false;
                    }
                }
                return tempQuestions.isEmpty();
            }
        }

        return false;
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
