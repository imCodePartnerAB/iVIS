package com.imcode.services.converters;

import com.imcode.entities.superclasses.AbstractIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by vitaly on 05.06.15.
 */
public class StringToEntityConverter implements ConverterFactory<String, AbstractIdEntity> {
    @Autowired
    private ApplicationContext context;

    @Override
    public <T extends AbstractIdEntity> Converter<String, T> getConverter(Class<T> targetType) {
        return null;
    }

    public static void main(String[] args) {

    }
//    private final class StringToEnumConverter<T extends AbstractIdEntity> implements Converter<String, T> {
//
//        private Class<T> enumType;
//
//        public StringToEnumConverter(Class<T> enumType) {
//            this.enumType = enumType;
//        }
//
//        public T convert(String source) {
//            return (T) Enum.valueOf(this.enumType, source.trim());
//        }
//    }
//    private final class implements Converter<String, T> {
//        @Autowired
//        SERVICE_TYPE service;
//
//        public SERVICE_TYPE getService() {
//            return service;
//        }
//
//        public void setService(SERVICE_TYPE service) {
//            this.service = service;
//        }
//
//        @Override
//        public T convert(String source) {
//            Long id = null;
//
//            try {
//                id = Long.parseLong(source);
//            } catch (NumberFormatException e) {
//                throw new IllegalArgumentException(source + " is not a Long value!")
//            }
//
//            T entity = getService().find(id);
//
//            return entity;
//        }

        //    @Override
//    public User convert( source) {
//        Long id = Long.valueOf(source);
//
//        User user = service.find(id);
//
//        return user;
//    }
//    }
}
