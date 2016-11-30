package com.imcode.misc.errors;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by vitaly on 26.02.15.
 */
public class Error implements Serializable{
    private String id;
    private String description;
    private Map<String, String[]> requestParams;

    public Error() { }

    public Error(String id) {
        this.id = id;
    }

    public Error(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Error(String id, String description, Map<String, String[]> requestParams) {
        this.id = id;
        this.description = description;
        this.requestParams = requestParams;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String[]> getrequestParams() {
        return requestParams;
    }

    public void setrequestParams(Map<String, String[]> requestParams) {
        this.requestParams = requestParams;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Error{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", requestParams=").append(requestParams);
        sb.append('}');
        return sb.toString();
    }
}
