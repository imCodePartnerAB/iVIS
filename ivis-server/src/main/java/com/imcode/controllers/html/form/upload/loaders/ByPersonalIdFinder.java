package com.imcode.controllers.html.form.upload.loaders;

import com.imcode.services.GenericService;
import com.imcode.services.PersonalizedService;
import org.springframework.core.convert.ConversionService;

import java.util.function.Function;

/**
 * Created by vitaly on 11.12.15.
 */
public class ByPersonalIdFinder<T> implements Function<String, T> {
    private final PersonalizedService<T> entityServise;

    public ByPersonalIdFinder(PersonalizedService<T> entityServise) {
        this.entityServise = entityServise;
    }

    @Override
    public T apply(String s) {
        return entityServise.findFirstByPersonalId(s);
    }
}
