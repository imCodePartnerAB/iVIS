package com.imcode.misc.errors;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * Created by vitaly on 26.02.15.
 */
public class ErrorFactory {
    private MessageSource messageSource;

    public Error getErrorWithDescription(int id, Locale locale) {
        if (locale == null) {
            locale = new Locale("en");
        }
        String key = String.valueOf(id);
        String description = messageSource.getMessage(key, null, "Unknown error", locale);
        Error error = new Error(key, description);

        return error;
    }

    public Error getErrorWithDescription(int id) {
        return getErrorWithDescription(id, null);
    }
    
    public Error getError(String id) {
        return new Error(id);
    }
    
    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
