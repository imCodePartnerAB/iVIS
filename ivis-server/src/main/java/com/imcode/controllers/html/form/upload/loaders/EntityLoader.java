package com.imcode.controllers.html.form.upload.loaders;

import com.imcode.controllers.converters.NotNullStringToCollectionConverter;
import com.imcode.controllers.html.form.upload.FileOption;
import com.imcode.services.AbstractService;
import com.imcode.services.GenericService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.validation.DataBinder;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Created by vitaly on 10.12.15.
 */
public class EntityLoader<T> {
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final Class<T> entityType;
    private ConversionService conversionService;
    private Set<String> allowedFieldSet;
    private GenericService<T, ?> entityServise;
    private Map<String, Function<String, T>> finders = new LinkedHashMap<>();
    private Supplier<T> newEntitySupplier;

    @SuppressWarnings("unchecked")
    public EntityLoader(Class<T> entityType, ConversionService conversionService) {
        this.entityType = entityType;
        this.conversionService = conversionService;
        this.newEntitySupplier = () -> {
            try {
                return entityType.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        finders.put("id", new ByIdFinder<>((GenericService<T, Long>) entityServise, Long.class, conversionService));
    }

    public EntityLoader(Class<T> entityType) {
        this(entityType, new DefaultConversionService());

    }

    public GenericService<T, ?> getEntityServise() {
        return entityServise;
    }

    public Map<String, Function<String, T>> getFinders() {
        return finders;
    }

    public void setFinders(Map<String, Function<String, T>> finders) {
        this.finders = finders;
    }

    public void setEntityServise(GenericService<T, ?> entityServise) {
        this.entityServise = entityServise;
    }

    public ConversionService getConversionService() {
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public Set<String> getAllowedFieldSet() {
        return allowedFieldSet;
    }

    public void setAllowedFieldSet(Set<String> allowedFieldSet) {
        this.allowedFieldSet = allowedFieldSet;
    }

    public void bindData(T target, Map<?, ?> data) {
        DataBinder binder = new DataBinder(target);
        binder.setConversionService(conversionService);
        binder.bind(new MutablePropertyValues(data));
    }

    public FieldSetMapper<T> getFieldSetMapper(FileOption fileOption) {
        return new FieldSetMapperImpl(fileOption);
    }

    public ItemWriter<T> getItemWriter() {
        return new ItemWriterImpl();
    }

    private Function<String, T> getEntityFinder(String finderName) {
        Function<String, T> finder = finders.get(finderName);
        return finder;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public Supplier<T> getNewEntitySupplier() {
        return newEntitySupplier;
    }

    public void setNewEntitySupplier(Supplier<T> newEntitySupplier) {
        this.newEntitySupplier = newEntitySupplier;
    }

    private class FieldSetMapperImpl implements FieldSetMapper<T> {
        private final FileOption fileOption;

        FieldSetMapperImpl(FileOption fileOption) {
            this.fileOption = fileOption;
        }

        @Override
        public T mapFieldSet(FieldSet fieldSet) throws BindException {
            T entity = null;
            Integer indexColumn = fileOption.getKeyColumn();
            String indexColumnName = fieldSet.getNames()[indexColumn];
            Function<String, T> finder = getEntityFinder(indexColumnName);

            if (finder == null) {
                throw new BindException(entityType, "finder '" + indexColumnName + "' not found.");
            }

            String indexColumnValue = fieldSet.readString(indexColumn);
            entity = finder.apply(indexColumnValue);

            if (entity == null) {
                entity = newEntitySupplier.get();
            }

            bindData(entity, fieldSet.getProperties());

            return entity;
        }
    }

    private class ItemWriterImpl implements ItemWriter<T> {
        @Override
        @SuppressWarnings("unchecked")
        public void write(List<? extends T> items) throws Exception {
            List<T> entityList = (List<T>) items;
            AbstractService<T, ?, ?> service = (AbstractService<T, ?, ?>) entityServise;
            for (int i = 0; i < entityList.size(); i++) {
                T entity = entityList.get(i);
                try {
                    entityList.set(i, service.saveWithoutFlush(entity));
                } catch (Exception e) {
                    logger.warning(e::getMessage);
                }
            }
            service.flush();
        }
    }

}
