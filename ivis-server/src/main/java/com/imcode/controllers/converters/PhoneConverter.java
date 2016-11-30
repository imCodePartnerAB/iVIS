package com.imcode.controllers.converters;

import com.imcode.entities.embed.Phone;
import com.imcode.entities.enums.CommunicationTypeEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by vitaly on 11.12.15.
 */
public class PhoneConverter implements Converter<String, Phone>{
    @Override
    public Phone convert(String source) {
        return Phone.of(CommunicationTypeEnum.HOME, source);
    }
}
