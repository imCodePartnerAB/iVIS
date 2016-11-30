package com.imcode.controllers.html.form.upload.loaders;

import com.imcode.services.NamedService;

import java.util.function.Function;

/**
 * Created by vitaly on 11.12.15.
 */
public class ByNameFinder<T> implements Function<String, T> {
    private final NamedService<T> entityServise;

    public ByNameFinder(NamedService<T> entityServise) {
        this.entityServise = entityServise;
    }

    @Override
    public T apply(String s) {
        return entityServise.findFirstByName(s);
    }
}
