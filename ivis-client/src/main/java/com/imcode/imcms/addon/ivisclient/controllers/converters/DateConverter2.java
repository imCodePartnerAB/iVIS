package com.imcode.imcms.addon.ivisclient.controllers.converters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vitaly on 23.06.15.
 */
@Component
public class DateConverter2 implements ConditionalGenericConverter {

    @Value("${DatePattern}")
    private String datePattern;

    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
//        Date -> String
        if (sourceType.getType() == (Date.class) && targetType.getType() == (String.class)) {
            return true;
        }
//        String -> Date
        if (targetType.getType() == (Date.class) && sourceType.getType() == (String.class)) {
            return true;
        }

        return false;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(Date.class, String.class));
        set.add(new ConvertiblePair(String.class, Date.class));

        return Collections.unmodifiableSet(set);
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.getType() == (Date.class)) {
            return getAsText((Date) source);
        } else {
            return setAsText((String) source);
        }

//        throw new IllegalArgumentException("Can't convert string \"" + source + "\" to Date.");
    }

    public Date setAsText(String value) {
        if (value == null || StringUtils.isBlank(value)) {
            return null;
        }

        try {
            return new SimpleDateFormat(datePattern).parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Can't convert string \"" + value + "\" to Date.");
        }
    }

    public String getAsText(Date value) {
        if (value == null) {
            return null;
        }

        return new SimpleDateFormat(datePattern).format(value);
    }
//    @Override
//    public Date convert(String source) {
//        Date result = null;
//
//        if (StringUtils.isEmpty(source)) {
//            return result;
//        }
//
//        try {
//            SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
//            result = dateFormatter.parse(source);
//            return result;
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Can't convert string \"" + source + "\" to Date.");
//        }
//
//    }
}
