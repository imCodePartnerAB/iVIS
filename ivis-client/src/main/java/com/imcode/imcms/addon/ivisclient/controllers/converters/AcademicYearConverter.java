package com.imcode.imcms.addon.ivisclient.controllers.converters;

import com.imcode.entities.AcademicYear;
import com.imcode.services.AcademicYearService;
import imcode.services.IvisServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by vitaly on 16.06.15.
 */
@Component
public class AcademicYearConverter implements Converter<String, AcademicYear> {
    @Autowired
    private IvisServiceFactory serviceFactory;

    @Override
    public AcademicYear convert(String source) {
        AcademicYearService service = serviceFactory.getService(AcademicYearService.class);
        Long id = Long.valueOf(source);
        AcademicYear entity = service.find(id);

        return entity;
    }
}
