package com.imcode.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Created by ruslan on 5/25/16.
 */
public class MessageOfException {
    private String localizedMsg;
    private String cause;
    private String stackTrace;
    private String message;

    public MessageOfException(Exception exception) {
        localizedMsg = exception.getLocalizedMessage();
        cause = exception.getCause().toString();
        stackTrace = Arrays.toString(exception.getStackTrace());
        message = exception.getMessage();
    }

    public String getLocalizedMsg() {
        return localizedMsg;
    }

    public void setLocalizedMsg(String localizedMsg) {
        this.localizedMsg = localizedMsg;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
