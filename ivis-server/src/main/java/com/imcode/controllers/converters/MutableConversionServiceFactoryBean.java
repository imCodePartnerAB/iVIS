package com.imcode.controllers.converters;

import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vitaly on 16.12.15.
 */

public class MutableConversionServiceFactoryBean extends ConversionServiceFactoryBean{
    private Map<Class<?>, Class<?>> removeConvertible = new HashMap<>();

    public Map<Class<?>, Class<?>> getRemoveConvertible() {
        return removeConvertible;
    }

    public void setRemoveConvertible(Map<Class<?>, Class<?>> removeConvertible) {
        this.removeConvertible = removeConvertible;
    }

    @Override
    protected GenericConversionService createConversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        //removing appropriate converters
        removeConvertible.entrySet().stream().forEach(entry->conversionService.removeConvertible(entry.getKey(), entry.getValue()));
        return conversionService;
    }
}
