package com.imcode.imcms.addon.ivisclient.controllers.converters;

/**
 * Created by vitaly on 17.06.15.
 */

import java.util.Collections;
import java.util.Set;

import com.imcode.entities.superclasses.AbstractIdEntity;
import com.imcode.services.GenericService;
import imcode.services.IvisServiceFactory;
import imcode.services.restful.ServiceInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * {@link org.springframework.core.convert.converter.Converter} to convert arbitrary input into domain classes managed
 * by Spring Data {@link CrudRepository}s. The implementation uses a {@link ConversionService} in turn to convert the
 * source type into the domain class' id type which is then converted into a domain class object by using a
 * {@link CrudRepository}.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
//TODO implement the IvisConversionService
public class IvisDomainClassConverter
        <T extends ConversionService & ConverterRegistry> implements
        ConditionalGenericConverter, ApplicationContextAware {

    private final T conversionService;
    private IvisServiceFactory serviceFactory;
    private ToEntityConverter toEntityConverter;
    private ToIdConverter toIdConverter;

    /**
     * Creates a new {@link IvisDomainClassConverter} for the given {@link ConversionService}.
     *
     * @param conversionService must not be {@literal null}.
     */
    public IvisDomainClassConverter(T conversionService) {

        Assert.notNull(conversionService, "ConversionService must not be null!");

        this.conversionService = conversionService;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.GenericConverter#getConvertibleTypes()
     */
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Object.class, Object.class));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.GenericConverter#convert(java.lang.Object, org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
     */
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {

        return serviceFactory.hasServiceFor(targetType.getType()) ? toEntityConverter.convert(source, sourceType,
                targetType) : toIdConverter.convert(source, sourceType, targetType);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
     */
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

        try {
            return toEntityConverter.matches(sourceType, targetType) || toIdConverter.matches(sourceType, targetType);
        } catch (ConversionMatchAbbreviationException o_O) {
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext context) {

        this.serviceFactory = context.getBean(IvisServiceFactory.class);

//        this.toEntityConverter = new ToEntityConverter(this.serviceFactory, this.conversionService);
        this.toEntityConverter = new ToEntityConverter();
        this.conversionService.addConverter(this.toEntityConverter);

//        this.toIdConverter = new ToIdConverter();
//        this.conversionService.addConverter(this.toIdConverter);
    }

    /**
     * Converter to create domain types from any source that can be converted into the domain types identifier type.
     *
     * @author Oliver Gierke
     * @since 1.10
     */
    private class ToEntityConverter implements ConditionalGenericConverter {

//        private final RepositoryInvokerFactory repositoryInvokerFactory;

        /**
         * Creates a new {@link ToEntityConverter} for the given {@link Repositories} and {@link ConversionService}.
         *
         * @param repositories must not be {@literal null}.
         * @param conversionService must not be {@literal null}.
         */
//        public ToEntityConverter(Repositories repositories, ConversionService conversionService) {
//            this.repositoryInvokerFactory = new DefaultRepositoryInvokerFactory(repositories, conversionService);
//        }

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

            if (sourceType.equals(targetType)) {
                return source;
            }

            Class<?> domainType = targetType.getType();

            ServiceInfo serviceInfo = serviceFactory.getServiceInfoFor(domainType);
            GenericService service = serviceInfo.getService();
//            RepositoryInvoker invoker = repositoryInvokerFactory.getInvokerFor(domainType);

//            return invoker.invokeFindOne(conversionService.convert(source, serviceInfo.getIdType()));
            return service.find(conversionService.convert(source, serviceInfo.getIdClass()));
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

//            if (!serviceFactory.hasRepositoryFor(targetType.getType())) {
            if (!serviceFactory.hasServiceFor(targetType.getType())) {
                return false;
            }

            if (sourceType.equals(targetType)) {
                return true;
            }

//            Class<?> rawIdType = serviceFactory.getRepositoryInformationFor(targetType.getType()).getIdType();
            Class<?> rawIdType = serviceFactory.getServiceInfoFor(targetType.getType()).getIdClass();

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

            if (sourceType.equals(targetType)) {
                return source;
            }

//            Class<?> domainType = sourceType.getType();

//            EntityInformation<Object, ?> entityInformation = serviceFactory.getEntityInformationFor(domainType);

//            return conversionService.convert(entityInformation.getId(source), targetType.getType());
            AbstractIdEntity entity = (AbstractIdEntity) source;
            return conversionService.convert(entity.getId(), targetType.getType());
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

//            if (!serviceFactory.hasRepositoryFor(sourceType.getType())) {
            if (!serviceFactory.hasServiceFor(sourceType.getType())) {
                return false;
            }

            if (sourceType.equals(targetType)) {
                return true;
            }

            if (targetType.hasAnnotation(ModelAttribute.class)) {
                return false;
            }

//            Class<?> rawIdType = serviceFactory.getRepositoryInformationFor(sourceType.getType()).getIdType();
            Class<?> rawIdType = serviceFactory.getServiceInfoFor(sourceType.getType()).getIdClass();

            return targetType.equals(TypeDescriptor.valueOf(rawIdType))
                    || conversionService.canConvert(rawIdType, targetType.getType());
        }
    }

    @SuppressWarnings("serial")
    private static final class ConversionMatchAbbreviationException extends RuntimeException {}
}
