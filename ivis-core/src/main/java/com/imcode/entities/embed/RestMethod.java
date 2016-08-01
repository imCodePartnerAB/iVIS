package com.imcode.entities.embed;

import com.imcode.entities.enums.RequestMethod;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ruslan on 01.08.16.
 */
@Embeddable
public class RestMethod implements Serializable {

    @Column(name = "rest_name")
    private String restName;

    @Column
    private String url;

    @Column(name = "request_method")
    @Enumerated(EnumType.STRING)
    private RequestMethod requestMethod;

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
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
}
