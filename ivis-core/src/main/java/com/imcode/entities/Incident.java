package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Created by ruslan on 29.04.16.
 */
@Entity
@Table(name = "dbo_incident")
public class Incident extends AbstractIdEntity<Long> implements Serializable {

    @Column(nullable = false)
    @NotNull(message = "title can not be null")
    private String title;

    @Column
    @NotNull(message = "description can not be null")
    private String description;

    @Column(name = "report_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("reported_date")
    private Date reportDay;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_incident_category_cross",
            joinColumns = @JoinColumn(name = "incidentId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    @Size(min = 1, message = "categories can not be null")
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_incident_pupil_cross",
            joinColumns = @JoinColumn(name = "incidentId"),
            inverseJoinColumns = @JoinColumn(name = "pupilId"))
    @Size(min = 1, message = "pupils can not be null")
    private Set<Pupil> pupils = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusId")
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "priorityId")
    @NotNull(message = "priority can not be null")
    private Priority priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "issueId")
    private Issue issue;

    @Column(name = "assigned_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("assigned_date")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date assignedDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "assigned_person_id")
    @JsonProperty("assigned_by")
    private Person assignedBy;

    @Column(name = "archived_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("archived_date")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date archivedDay;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "archived_user_id")
    @JsonProperty("archived_by")
    private User archivedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_user_id")
    @JsonProperty("reported_by")
    private User reportedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modified_user_id")
    @JsonProperty("modified_by")
    private User modifiedBy;

    @Column(name = "modified_day")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty("modified_date")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date modifiedDay;

    public Incident() {
    }

    public Incident(Long aLong) {
        super(aLong);
    }

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

    public Date getReportDay() {
        return reportDay;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setReportDay(Date reportDay) {
        this.reportDay = reportDay;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getAssignedDay() {
        return assignedDay;
    }

    public void setAssignedDay(Date assignedDay) {
        this.assignedDay = assignedDay;
    }

    public Person getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(Person assignedBy) {
        this.assignedBy = assignedBy;
    }

    public Date getArchivedDay() {
        return archivedDay;
    }

    public void setArchivedDay(Date archivedDay) {
        this.archivedDay = archivedDay;
    }

    public Date getModifiedDay() {
        return modifiedDay;
    }

    public void setModifiedDay(Date modifiedDay) {
        this.modifiedDay = modifiedDay;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public User getArchivedBy() {
        return archivedBy;
    }

    public void setArchivedBy(User archivedBy) {
        this.archivedBy = archivedBy;
    }

    public Set<Pupil> getPupils() {
        return pupils;
    }

    public void setPupils(Set<Pupil> pupils) {
        this.pupils = pupils;
    }
}
