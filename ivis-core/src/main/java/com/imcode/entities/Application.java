package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imcode.entities.embed.Decision;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.listners.AuditableModelListener;
import com.imcode.entities.listners.DatedEntityListner;
import com.imcode.entities.superclasses.AbstractJpaDatedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vitaly on 14.05.15.
 */
@Entity
@Table(name = "dbo_application")
@EntityListeners({AuditableModelListener.class, DatedEntityListner.class})
public class Application extends AbstractJpaDatedEntity<Long> implements Serializable {

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "applicationFormId")
    @JsonIgnoreProperties(value = "applications")
    @JsonProperty("application_form")
    private ApplicationForm applicationForm;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "submittedUserId")
    @JsonProperty("submitted_user")
    private User submittedUser;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "regardingUserId")
    @JsonProperty("regarding_user")
    private User regardingUser;

    @Column
    @JsonProperty("registration_number")
    private Long registrationNumber;

    @Embedded
    private Decision decision;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "handledUserId")
    @JsonProperty("handled_user")
    private Person handledUser;


    public Application() {
    }

    public ApplicationForm getApplicationForm() {
        return applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForm) {
        this.applicationForm = applicationForm;
    }

    public User getSubmittedUser() {
        return submittedUser;
    }

    public void setSubmittedUser(User submittedUser) {
        this.submittedUser = submittedUser;
    }

    public User getRegardingUser() {
        return regardingUser;
    }

    public void setRegardingUser(User regardingUser) {
        this.regardingUser = regardingUser;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Decision getDecision() {
        return decision;
    }

    public void setDecision(Decision decision) {
        this.decision = decision;
    }

    public Person getHandledUser() {
        return handledUser;
    }

    public void setHandledUser(Person handledUser) {
        this.handledUser = handledUser;
    }

    @JsonIgnore
    @Transient
    public Decision.Status getStatus() {
        if (decision != null) {
            return decision.getStatus();
        }

        return null;
    }

    @JsonIgnore
    @Transient
    public void setStatus(Decision.Status status) {
        if (decision == null) {
            decision = new Decision();
        }

        decision.setStatus(status);
    }

    @Override
    public boolean deepEquals(JpaEntity entity) {
        if (!super.deepEquals(entity)) {
            return false;
        }

        Application that = (Application) entity;

        return Objects.equals(this.id, that.id)
                && Objects.equals(this.decision, that.decision)
                && Objects.equals(this.regardingUser, that.regardingUser)
                && Objects.equals(this.registrationNumber, that.registrationNumber)
                && Objects.equals(this.handledUser, that.handledUser)
                && Objects.equals(this.submittedUser, that.submittedUser)
                && JpaEntity.deepEquals(this.applicationForm, that.applicationForm);
    }

    @JsonIgnore
    public Optional<ApplicationFormQuestion> findQuestion(String name, boolean ignoreCase) {
        Predicate<ApplicationFormQuestion> filter = ignoreCase
                ? q -> name.equals(q.getName())
                : q -> name.equalsIgnoreCase(q.getName());

        return findQuestion(filter);
    }

    @JsonIgnore
    public Optional<ApplicationFormQuestion> findQuestion(String name, String value, boolean ignoreCase) {
        Predicate<ApplicationFormQuestion> filter = ignoreCase
                ? q -> name.equals(q.getName())
                : q -> name.equalsIgnoreCase(q.getName());
        filter.and(applicationFormQuestion -> applicationFormQuestion.getValue() != null
                && applicationFormQuestion.getValue().toLowerCase().contains(value));

        return findQuestion(filter);
    }

    @JsonIgnore
    public Optional<ApplicationFormQuestion> findQuestion(Predicate<ApplicationFormQuestion> filter) {
        return getQuestionStream().filter(filter).findFirst();
    }

    @JsonIgnore
    public List<ApplicationFormQuestion> getQuestionList() {
        return getQuestionStream().collect(Collectors.toList());
    }

@JsonIgnore
    public Stream<ApplicationFormQuestion> getQuestionStream() {
        return applicationForm.getSteps().stream()
                .flatMap(applicationFormStep -> applicationFormStep.getQuestionGroups()
                        .stream()).flatMap(questionGroup -> questionGroup.getQuestions().stream());
    }
}

