package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractNamedEntity;
import com.imcode.entities.superclasses.AbstractSortableNamedEntity;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.util.*;

/**
 * Created by vitaly on 10.03.16.
 */
@Entity
@Table(name = "dbo_application_form_question_group")
public class ApplicationFormQuestionGroup extends AbstractSortableNamedEntity<Long>{
    @Column
    private String text;

    @OrderBy("sortOrder ASC, text ASC")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "questionGroup")
    private List<ApplicationFormQuestion> questions = new ArrayList<>();

    @Column
    @JsonProperty("question_type")
    private String questionType;

    @ManyToOne
    private ApplicationFormStep step;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ApplicationFormQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ApplicationFormQuestion> questions) {
        this.questions = questions;
    }

    @JsonIgnoreProperties(value = "question_groups")
    public ApplicationFormStep getStep() {
        return step;
    }

    public void setStep(ApplicationFormStep step) {
        this.step = step;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        ApplicationFormQuestionGroup that = (ApplicationFormQuestionGroup) entity;

        return Objects.equals(this.text, that.text)
                && Objects.equals(this.questionType, that.questionType)
                && JpaEntity.deepEquals(this.questions, that.questions);
    }

    public void addQuestion(ApplicationFormQuestion question) {
        questions.add(question);
    }
}
