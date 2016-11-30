package com.imcode.misc;

/**
 * Created by vitaly on 26.02.15.
 */
@Deprecated
public class NormalResponse extends Response {
    private Object response;

    public NormalResponse() {
    }

    public NormalResponse(Object response) {
        this.response = response;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
