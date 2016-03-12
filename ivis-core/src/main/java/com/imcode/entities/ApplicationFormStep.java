package com.imcode.entities;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractSortableNamedEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by vitaly on 10.03.16.
 */
@Entity
@Table(name = "dbo_application_form_step")
public class ApplicationFormStep extends AbstractSortableNamedEntity<Long> {
    @Column
    private String text;

    @OrderBy("sortOrder ASC, text ASC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "step")
    private List<ApplicationFormQuestionGroup> questionGroups = new ArrayList<>();

    @ManyToOne
    private ApplicationForm applicationForm;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ApplicationFormQuestionGroup> getQuestionGroups() {
        return questionGroups;
    }

    public void setQuestionGroups(List<ApplicationFormQuestionGroup> questionGroups) {
        this.questionGroups = questionGroups;
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        ApplicationFormStep that = (ApplicationFormStep) entity;

        return Objects.equals(this.text, that.text)
                && JpaEntity.deepEquals(this.questionGroups, that.questionGroups);
    }
}
