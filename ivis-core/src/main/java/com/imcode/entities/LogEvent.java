package com.imcode.entities;

import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * This represents an Event to be Logged
 *
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
@Entity
@Table(name = "dbo_log_event")
public class LogEvent extends AbstractIdEntity<Long> {

//    @Transient
//    public transient JpaEntity<Long> entity;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(nullable = false)
    private String entityClassName;

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Action action;

    @Column
    private String fieldName;

    @Column
    private String previousValue;

    @Column
    private String newValue;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

//    @Column
//    public Long accountId;

    public LogEvent() {
    }

    public LogEvent(Long aLong) {
        super(aLong);
    }

    public LogEvent(JpaEntity<Long> entity, Action action, String fieldName, String previousValue, String newValue, User user) {
//        this.entity = entity;
        this.entityClassName = entity.getClass().getName();
        this.timestamp = new Date();
        this.action = action;
        this.entityId = entity.getId();
        this.fieldName = fieldName;
        this.previousValue = previousValue;
        this.newValue = newValue;
        this.user = user;
//        this.accountId = accountId;
    }


    public enum Action {
        CREATE,
        MODIFY,
        DELETE
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
