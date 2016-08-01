package com.imcode.entities;

import com.imcode.entities.enums.RequestMethod;
import com.imcode.entities.superclasses.AbstractNamedEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 01.08.16.
 */
@Entity
@Table(name = "dbo_entity_rest_method")
public class RestMethod extends AbstractNamedEntity<Long> implements Serializable {

    @Column
    private String url;

    @Column(name = "request_method")
    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "restMethod")
    private ServiceMethod serviceMethod;

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

    public ServiceMethod getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(ServiceMethod serviceMethod) {
        this.serviceMethod = serviceMethod;
    }
}
