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
public class EntityProviderMethod extends AbstractNamedEntity<Long> implements Serializable {

    @Column(name = "return_parameter")
    private String returnParameter;

    @Column(name = "in_parameters")
    private String inParameters;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_service_information_id")
    private EntityProviderInformation entityProviderInformation;

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

    public EntityProviderInformation getEntityProviderInformation() {
        return entityProviderInformation;
    }

    public void setEntityProviderInformation(EntityProviderInformation entityProviderInformation) {
        this.entityProviderInformation = entityProviderInformation;
    }

    public RestMethod getRestMethod() {
        return restMethod;
    }

    public void setRestMethod(RestMethod restMethod) {
        this.restMethod = restMethod;
    }

    public String getInParameters() {
        return inParameters;
    }

    public void setInParameters(String inParameters) {
        this.inParameters = inParameters;
    }

}
