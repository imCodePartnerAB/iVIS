package imcode.services.converters;

/**
 * Created by vitaly on 17.06.15.
 */

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Set;

import com.imcode.entities.superclasses.AbstractIdEntity;
import imcode.services.IvisServiceFactory;
import imcode.services.restful.ServiceInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * {@link org.springframework.core.convert.converter.Converter} to convert arbitrary input into domain classes managed
 * by Spring Data {@link CrudRepository}s. The implementation uses a {@link ConversionService} in turn to convert the
 * source type into the domain class' id type which is then converted into a domain class object by using a
 * {@link CrudRepository}.
 *
 * @author Oliver Gierke
 * @author Thomas Darimont
 */
public class IvisIdToDomainClassConverter
        <T extends ConversionService & ConverterRegistry> implements
        ConditionalGenericConverter, ApplicationContextAware {

    private final T conversionService;
    private IvisServiceFactory serviceFactory;
    private ToEntityConverter toEntityConverter;

    /**
     * Creates a new {@link IvisIdToDomainClassConverter} for the given {@link ConversionService}.
     *
     * @param conversionService must not be {@literal null}.
     */
    public IvisIdToDomainClassConverter(T conversionService) {

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

        return toEntityConverter.convert(source, sourceType,
                targetType);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
     */
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

        try {
            return toEntityConverter.matches(sourceType, targetType);
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

        this.toEntityConverter = new ToEntityConverter();
        this.conversionService.addConverter(this.toEntityConverter);

    }

    /**
     * Converter to create domain types from any source that can be converted into the domain types identifier type.
     *
     * @author Oliver Gierke
     * @since 1.10
     */
    private class ToEntityConverter implements ConditionalGenericConverter {


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

            if (source == null || !StringUtils.hasText(source.toString()) && !sourceType.equals(targetType)) {
                return null;
            }

            if (sourceType.equals(targetType)) {
                return source;
            }

            Class<?> domainType = targetType.getType();

            ServiceInfo serviceInfo = serviceFactory.getServiceInfoFor(domainType);
            Object id = conversionService.convert(source, serviceInfo.getIdClass());

            try {
                Object instance = targetType.getObjectType().newInstance();
                AbstractIdEntity abstractIdEntity = (AbstractIdEntity) instance;
                abstractIdEntity.setId((Serializable) id);
                return abstractIdEntity;
            } catch (Exception e) {
                return null;
            }

        }

        /*
         * (non-Javadoc)
         * @see org.springframework.core.convert.converter.ConditionalConverter#matches(org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
         */
        @Override
        public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

            if (!serviceFactory.hasServiceFor(targetType.getType())) {
                return false;
            }

            if (sourceType.equals(targetType)) {
                return true;
            }

            Class<?> rawIdType = serviceFactory.getServiceInfoFor(targetType.getType()).getIdClass();

            if (sourceType.equals(TypeDescriptor.valueOf(rawIdType))
                    || conversionService.canConvert(sourceType.getType(), rawIdType)) {
                return true;
            }

            return false;
        }
    }

    @SuppressWarnings("serial")
    private static final class ConversionMatchAbbreviationException extends RuntimeException {}
}
