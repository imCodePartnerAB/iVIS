package com.imcode.entities;

import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.io.Serializable;
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
    @JoinColumn(name = "userId")
    private User responsiblePerson;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "dbo_issues_authorized_persons_cross",
            joinColumns = @JoinColumn(name = "issueId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> authorizedUsers = new HashSet<>();

    @OneToMany(mappedBy = "issue")
    private Set<Incident> incidents = new HashSet<>();

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

    public User getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(User responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public Set<User> getAuthorizedUsers() {
        return authorizedUsers;
    }

    public void setAuthorizedUsers(Set<User> authorizedUsers) {
        this.authorizedUsers = authorizedUsers;
    }

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }
}

