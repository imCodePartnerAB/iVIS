package com.imcode.controllers.converters;

import com.imcode.entities.embed.Email;
import com.imcode.entities.enums.CommunicationTypeEnum;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by vitaly on 11.12.15.
 */
public class EmailConverter implements Converter<String, Email>{
    @Override
    public Email convert(String source) {
        return Email.of(CommunicationTypeEnum.HOME, source);
    }
}
