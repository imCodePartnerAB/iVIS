package com.imcode.services.converters;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by vitaly on 04.06.15.
 */
//@Component
abstract public class AbstractIdentifierEntityConverter<T extends AbstractIdEntity, SERVICE_TYPE extends GenericService<T, Long>> implements Converter<String, T> {
    @Autowired
    SERVICE_TYPE service;

    public SERVICE_TYPE getService() {
        return service;
    }

    public void setService(SERVICE_TYPE service) {
        this.service = service;
    }

    @Override
    public T convert(String source) {
        Long id = null;

        try {
            id = Long.parseLong(source);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(source + " is not a Long value!");
        }

        T entity = getService().find(id);

        return entity;
    }

    //    @Override
//    public User convert( source) {
//        Long id = Long.valueOf(source);
//
//        User user = service.find(id);
//
//        return user;
//    }
}
