package com.imcode.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.imcode.entities.interfaces.JpaEntity;
import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.json.EntityVersionDeserializer;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 * This represents an Event to be Logged
 *
 *
 * @author Gonto
 * @since Dec 11, 2012
 */
@JsonDeserialize(using = EntityVersionDeserializer.class)
@Entity
@Table(name = "dbo_entity_version")
public class EntityVersion extends AbstractIdEntity<Long> {

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(nullable = false)
    @JsonProperty("entity_class")
    private Class<?> entityClass;

    @Column(nullable = false)
    @JsonProperty("entity_id")
    private Long entityId;

    @Column
    @Lob
    private Serializable entity;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private Action action;
//
//    @Column
//    private String fieldName;
//
//    @Column
//    private String previousValue;
//
//    @Column
//    private String newValue;

//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "userId")
//    private User user;

//    @Column
//    public Long accountId;

    public EntityVersion() {
    }

    public EntityVersion(Long aLong) {
        super(aLong);
    }

    public EntityVersion(JpaEntity<Long> entity) {
//        this.entity = entity;
        this.entityClass = entity.getClass();
        this.timestamp = new Date();
//        this.action = action;
        this.entityId = entity.getId();
        this.entity = entity;
//        this.fieldName = fieldName;
//        this.previousValue = previousValue;
//        this.newValue = newValue;
//        this.user = user;
//        this.accountId = accountId;
    }


//    public enum Action {
//        CREATE,
//        MODIFY,
//        DELETE
//    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

//    public String getEntityClassName() {
//        return entityClassName;
//    }
//
//    public void setEntityClassName(String entityClassName) {
//        this.entityClassName = entityClassName;
//    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Serializable getEntity() {
        return entity;
    }

    public void setEntity(JpaEntity<?> entity) {
        this.entity = entity;
    }

    public Class<?> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

//    public Action getAction() {
//        return action;
//    }
//
//    public void setAction(Action action) {
//        this.action = action;
//    }
//
//    public String getFieldName() {
//        return fieldName;
//    }
//
//    public void setFieldName(String fieldName) {
//        this.fieldName = fieldName;
//    }
//
//    public String getPreviousValue() {
//        return previousValue;
//    }
//
//    public void setPreviousValue(String previousValue) {
//        this.previousValue = previousValue;
//    }
//
//    public String getNewValue() {
//        return newValue;
//    }
//
//    public void setNewValue(String newValue) {
//        this.newValue = newValue;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

//    public static void main(String[] args) throws IOException {
//        Role role = new Role(5L, "ROLE_USER");
//        EntityVersion version = new EntityVersion(role);
//        ObjectMapper mapper = new ObjectMapper();
//        String versionString = mapper.writeValueAsString(version);
//        EntityVersion version1 = mapper.readValue(versionString, EntityVersion.class);
//        System.out.println(version1);
//
//    }
}
