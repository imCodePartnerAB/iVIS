package com.imcode.imcms.addon.ivisclient.controllers.converters;

import com.imcode.entities.School;
import com.imcode.services.GenericService;
import com.imcode.services.SchoolService;
import imcode.services.IvisServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by vitaly on 16.06.15.
 */
@Component
public class SchoolConverter implements Converter<String, School> {
    @Autowired
    private IvisServiceFactory serviceFactory;

    @Override
    public School convert(String source) {
        SchoolService service = serviceFactory.getService(SchoolService.class);
        Long id = Long.valueOf(source);
        School entity = service.find(id);

        return entity;
    }
}
