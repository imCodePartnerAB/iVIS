package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ruslan on 5/11/16.
 */
@Entity
@Table(name = "dbo_issue")
public class Issue extends AbstractIdEntity<Long> implements Serializable {

    @Column(nullable = false)
    @NotNull(message = "title can not be null")
    private String title;

    @Column(columnDefinition = "text")
    @NotNull(message = "description can not be null")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    @NotNull(message = "status can not be null")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    @JsonProperty("responsible_person")
    @NotNull(message = "responsible person can not be null")
    private Person responsiblePerson;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_issues_authorized_persons_cross",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "personId"))
    @JsonProperty("authorized_persons")
    @Size(min = 1, message = "authorized persons can not be null")
    private Set<Person> authorizedPersons = new HashSet<>();

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "issue")
    @Size(min = 1, message = "incidents can not be null")
    private Set<Incident> incidents = new HashSet<>();

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("reported_date")
    private Date reportDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_person_id")
    @JsonProperty("reported_by")
    private Person reportedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modified_person_id")
    @JsonProperty("modified_by")
    private Person modifiedBy;

    @Column(name = "modified_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("modified_date")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date modifiedDay;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "issue")
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_issue_category_cross",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priorityId")
    private Priority priority;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_issue_pupil_cross",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "pupilId"))
    private Set<Pupil> pupils = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Person getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public Set<Person> getAuthorizedPersons() {
        return authorizedPersons;
    }

    public void setAuthorizedPersons(Set<Person> authorizedPersons) {
        this.authorizedPersons = authorizedPersons;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public Date getReportDay() {
        return reportDay;
    }

    public void setReportDay(Date reportDay) {
        this.reportDay = reportDay;
    }

    public Person getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(Person reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Person getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Person modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDay() {
        return modifiedDay;
    }

    public void setModifiedDay(Date modifiedDay) {
        this.modifiedDay = modifiedDay;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }

}

