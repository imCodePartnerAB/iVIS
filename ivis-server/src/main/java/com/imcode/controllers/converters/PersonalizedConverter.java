package com.imcode.controllers.converters;

import com.imcode.entities.interfaces.JpaPersonalizedEntity;
import com.imcode.services.PersonalizedService;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by vitaly on 11.12.15.
 */
public abstract class PersonalizedConverter<T extends JpaPersonalizedEntity<Long>> implements Converter<String, T> {
    private PersonalizedService<T> entityService;

    public PersonalizedConverter(PersonalizedService<T> entityService) {
        this.entityService = entityService;
    }

    @Override
    public T convert(String source) {
        return entityService.findFirstByPersonalId(source);
    }
}
