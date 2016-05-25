package com.imcode.exceptions;

/**
 * Created by ruslan on 5/25/16.
 */
public class MessageOfException {
    private String title;

    private String exceptionMsg;

    public MessageOfException(String title, String exceptionMsg) {
        this.title = title;
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
