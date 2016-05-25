package com.imcode.exceptions;

/**
 * Created by ruslan on 5/25/16.
 */
public class MessagingException extends Exception {
    private MessageOfException exceptionMassage;

    public MessagingException(String title, String exceptionMsg) {
        exceptionMassage = new MessageOfException(title, exceptionMsg);
    }

    public MessageOfException getExceptionMeassage() {
        return exceptionMassage;
    }

    public void setExceptionMeassage(MessageOfException exceptionMeassage) {
        this.exceptionMassage = exceptionMeassage;
    }
}
