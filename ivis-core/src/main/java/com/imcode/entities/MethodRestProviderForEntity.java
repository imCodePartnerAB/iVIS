package com.imcode.entities;

import org.springframework.web.bind.annotation.RequestMethod;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_method_rest_provider_for_entity")
public class MethodRestProviderForEntity extends AbstractNamedEntity<Long> implements Serializable {

    @ElementCollection(fetch = FetchType.EAGER, targetClass = String.class)
    @MapKeyColumn(name = "name", length = 100)
    @Column(name = "type")
    @CollectionTable(name = "dbo_method_in_parameters", joinColumns = @JoinColumn(name = "method_id"))
    private Map<String, String> inParameters = new LinkedHashMap<String, String>();

    @Column(name = "out_parameter")
    private String outParameter;

    @Column
    private String url;

    @Column(name = "request_method")
    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entity_rest_provider_info_id")
    private EntityRestProviderInformation entityRestProviderInformation;

    public Map<String, String> getInParameters() {
        return inParameters;
    }

    public void setInParameters(Map<String, String> inParameters) {
        this.inParameters = inParameters;
    }

    public String getOutParameter() {
        return outParameter;
    }

    public void setOutParameter(String outParameter) {
        this.outParameter = outParameter;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public EntityRestProviderInformation getEntityRestProviderInformation() {
        return entityRestProviderInformation;
    }

    public void setEntityRestProviderInformation(EntityRestProviderInformation entityRestProviderInformation) {
        this.entityRestProviderInformation = entityRestProviderInformation;
    }

    //Check equals without id
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodRestProviderForEntity that = (MethodRestProviderForEntity) o;

        if (!inParameters.equals(that.inParameters)) return false;
        if (!outParameter.equals(that.outParameter)) return false;
        if (!url.equals(that.url)) return false;
        if (requestMethod != that.requestMethod) return false;
        if (!name.equals(that.name)) return false;
        return entityRestProviderInformation.equals(that.entityRestProviderInformation);

    }
}