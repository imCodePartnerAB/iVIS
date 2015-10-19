package com.imcode.entities;

import com.imcode.entities.embed.ApplicationFormQuestion;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.util.Set;
import java.util.SortedSet;

/**
 * Created by vitaly on 14.10.15.
 */
@Entity
@Table(name = "dbo_application_form")
public class ApplicationForm extends AbstractNamedEntity<Long> {

    @Column
    private Integer version;

    @ElementCollection
    @OrderBy("sortOrder ASC")
    @CollectionTable(name = "dbo_application_form_question",
            joinColumns = @JoinColumn(name = "applicationFormId"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"applicationFormId", "xsdElementName"}))
    private Set<ApplicationFormQuestion> questions;


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
}
