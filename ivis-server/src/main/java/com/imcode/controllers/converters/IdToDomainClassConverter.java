package com.imcode.controllers.converters;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.support.*;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.Set;

/**
 * Created by vitaly on 19.06.15.
 */
public class IdToDomainClassConverter<T extends ConversionService & ConverterRegistry> extends DomainClassConverter<T>{

        private final T conversionService;
        private ToEntityConverter toEntityConverter;
        private ToIdConverter toIdConverter;
        private Repositories repositories;


    /**
     * Creates a new {@link DomainClassConverter} for the given {@link ConversionService}.
     *
     * @param conversionService must not be {@literal null}.
     */
    public IdToDomainClassConverter(T conversionService) {
        super(conversionService);
        this.conversionService = conversionService;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) {

        this.repositories = new Repositories(context);

        this.toEntityConverter = new ToEntityConverter(this.repositories, this.conversionService);
        this.conversionService.addConverter(this.toEntityConverter);

        this.toIdConverter = new ToIdConverter();
        this.conversionService.addConverter(this.toIdConverter);

    }

    public boolean isTypesEqual(TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (sourceType.equals(targetType)) {
            return true;
        }

        if (sourceType.getType() == null && sourceType.getType() == targetType.getType()) {
            return true;
        }

        if (sourceType.getType() != null && sourceType.getType().equals(targetType.getType())) {
            return true;
        }

        return false;
    }

    /**
     * Converter to create domain types from any source that can be converted into the domain types identifier type.
     *
     * @author Oliver Gierke
     * @since 1.10
     */
    private class ToEntityConverter implements ConditionalGenericConverter {

        private final RepositoryInvokerFactory repositoryInvokerFactory;

        /**
         * Creates a new {@link ToEntityConverter} for the given {@link Repositories} and {@link ConversionService}.
         *
         * @param repositories      must not be {@literal null}.
         * @param conversionService must not be {@literal null}.
         */
        public ToEntityConverter(Repositories repositories, ConversionService conversionService) {
            this.repositoryInvokerFactory = new DefaultRepositoryInvokerFactory(repositories, conversionService);
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.GenericConverter#getConvertibleTypes()
         */
        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.GenericConverter#convert(java.lang.Object, org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

            if (source == null || !StringUtils.hasText(source.toString())) {
                return null;
            }

//            if (sourceType.equals(targetType)) {
            if (isTypesEqual(sourceType, targetType)) {
                return source;
            }

            Class<?> domainType = targetType.getType();

            RepositoryInformation info = repositories.getRepositoryInformationFor(domainType);
            RepositoryInvoker invoker = repositoryInvokerFactory.getInvokerFor(domainType);

            return invoker.invokeFindOne(conversionService.convert(source, info.getIdType()));
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

            if (!repositories.hasRepositoryFor(targetType.getType())) {
                return false;
            }

//            if (sourceType.equals(targetType)) {
//                return true;
//            }
            if (isTypesEqual(sourceType, targetType)) {
                return true;
            }

            Class<?> rawIdType = repositories.getRepositoryInformationFor(targetType.getType()).getIdType();

            if (sourceType.equals(TypeDescriptor.valueOf(rawIdType))
                    || conversionService.canConvert(sourceType.getType(), rawIdType)) {
                return true;
            }

            // Throw an exception to indicate it should have matched an no further resolution should be tried
//            throw new ConversionMatchAbbreviationException();
            return false;
        }
    }

    /**
     * Converter to turn domain types into their identifiers or any transitively convertible type.
     *
     * @author Oliver Gierke
     * @since 1.10
     */
    private class ToIdConverter implements ConditionalGenericConverter {

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.GenericConverter#getConvertibleTypes()
         */
        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.GenericConverter#convert(java.lang.Object, org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

            if (source == null || !StringUtils.hasText(source.toString())) {
                return null;
            }

//            if (sourceType.equals(targetType)) {
            if (isTypesEqual(sourceType, targetType)) {
                return source;
            }

            Class<?> domainType = sourceType.getType();

            EntityInformation<Object, ?> entityInformation = repositories.getEntityInformationFor(domainType);

            return conversionService.convert(entityInformation.getId(source), targetType.getType());
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

            if (!repositories.hasRepositoryFor(sourceType.getType())) {
                return false;
            }

            if (sourceType.equals(targetType)) {
                return true;
            }

            Class<?> rawIdType = repositories.getRepositoryInformationFor(sourceType.getType()).getIdType();

            return targetType.equals(TypeDescriptor.valueOf(rawIdType))
                    || conversionService.canConvert(rawIdType, targetType.getType());
        }
    }

    @SuppressWarnings("serial")
    private static final class ConversionMatchAbbreviationException extends RuntimeException {
    }
}
