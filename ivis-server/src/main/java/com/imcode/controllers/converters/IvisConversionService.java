package com.imcode.controllers.converters;

import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Collection;

/**
 * Created by vitaly on 16.12.15.
 */
@Deprecated
public class IvisConversionService extends DefaultConversionService{
    public IvisConversionService() {
        super();
        this.removeConvertible(String.class, Collection.class);
        this.addConverter(new NotNullStringToCollectionConverter(this));
    }
}
