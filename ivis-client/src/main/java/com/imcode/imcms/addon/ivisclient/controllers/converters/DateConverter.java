package com.imcode.imcms.addon.ivisclient.controllers.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vitaly on 23.06.15.
 */
@Component
@Deprecated
public class DateConverter implements Converter<String, Date>{

    @Value("${DatePattern}")
    private String datePattern;

    @Override
    public Date convert(String source) {
        Date result = null;

        if (StringUtils.isEmpty(source)) {
            return result;
        }

        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
            result = dateFormatter.parse(source);
            return result;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't convert string \"" + source + "\" to Date.");
        }

    }
}
