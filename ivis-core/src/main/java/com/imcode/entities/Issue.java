package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
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
    private String title;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "personId")
    @JsonProperty("responsible_person")
    private Person responsiblePerson;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_issues_authorized_persons_cross",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "personId"))
    @JsonProperty("authorized_persons")
    private Set<Person> authorizedPersons = new HashSet<>();

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "issue")
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
}

