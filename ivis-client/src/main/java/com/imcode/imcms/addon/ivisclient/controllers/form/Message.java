package com.imcode.imcms.addon.ivisclient.controllers.form;

/**
 * Created by vitaly on 08.04.15.
 */
public class Message {
    private String message;
    private MessageType type;

    public Message() { }

    public Message(MessageType type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

}

