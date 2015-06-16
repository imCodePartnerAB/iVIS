package com.imcode.imcms.addon.ivisclient.controllers.converters;

import com.imcode.entities.SchoolClass;
import com.imcode.services.SchoolClassService;
import imcode.services.IvisServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by vitaly on 16.06.15.
 */
@Component
public class SchoolClassConverter implements Converter<String, SchoolClass> {
    @Autowired
    private IvisServiceFactory serviceFactory;

    @Override
    public SchoolClass convert(String source) {
        SchoolClassService service = serviceFactory.getService(SchoolClassService.class);
        Long id = Long.valueOf(source);
        SchoolClass entity = service.find(id);

        return entity;
    }
}
