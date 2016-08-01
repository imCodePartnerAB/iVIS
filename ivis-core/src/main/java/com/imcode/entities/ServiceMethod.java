package com.imcode.entities;

import com.imcode.entities.embed.RestMethod;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_entity_service_method")
public class ServiceMethod extends AbstractNamedEntity<Long> implements Serializable {

    @Column(name = "return_parameter")
    private String returnParameter;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_service_information_id")
    private EntityServiceInformation entityServiceInformation;

    @Embedded
    private RestMethod restMethod;

    public String getReturnParameter() {
        return returnParameter;
    }

    public void setReturnParameter(String returnParameter) {
        this.returnParameter = returnParameter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntityServiceInformation getEntityServiceInformation() {
        return entityServiceInformation;
    }

    public void setEntityServiceInformation(EntityServiceInformation entityServiceInformation) {
        this.entityServiceInformation = entityServiceInformation;
    }

    public RestMethod getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(RestMethod restMethod) {
        this.restMethod = restMethod;
    }
}
