package com.imcode.controllers.html.form.upload.loaders;

import com.imcode.services.GenericService;
import org.springframework.core.convert.ConversionService;

import java.util.function.Function;

/**
 * Created by vitaly on 11.12.15.
 */
public class ByIdFinder<T, ID> implements Function<String, T> {
    private final GenericService<T, ID> entityServise;
    private final Class<ID> idClass;
    private final ConversionService conversionService;

    public ByIdFinder(GenericService<T, ID> entityServise, Class<ID> idClass, ConversionService conversionService) {
        this.entityServise = entityServise;
        this.idClass = idClass;
        this.conversionService = conversionService;
    }

    @Override
    public T apply(String s) {
        ID indexValue = conversionService.convert(s, idClass);
        return entityServise.find(indexValue);
    }
}
