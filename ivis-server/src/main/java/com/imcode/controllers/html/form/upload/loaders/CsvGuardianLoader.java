package com.imcode.controllers.html.form.upload.loaders;

import com.imcode.controllers.html.form.upload.FileOption;
import com.imcode.entities.Guardian;
import com.imcode.services.GuardianService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by vitaly on 10.12.15.
 */
@Deprecated
public class CsvGuardianLoader{
    private ConversionService conversionService;
    private Set<String> allowedFieldSet;
    private GuardianService entityServise;
    public Guardian bindData(Guardian target, Map<?, ?> data) {
        DataBinder binder = new DataBinder(target);
        binder.setConversionService(conversionService);
        binder.bind(new MutablePropertyValues(data));
        return target;
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


    public FieldSetMapper<Guardian> getFieldSetMapper(FileOption fileOption) {
        return new GuardianFieldSetMapper(fileOption);
    }

    public ItemWriter<Guardian> getItemWriter() {
        return new GuardianItemWriter();
    }

    public void setEntityServise(GuardianService entityServise) {
        this.entityServise = entityServise;
    }

    private class GuardianFieldSetMapper implements FieldSetMapper<Guardian> {
        private final FileOption fileOption;

        GuardianFieldSetMapper(FileOption fileOption) {
            this.fileOption = fileOption;
        }

        @Override
        public Guardian mapFieldSet(FieldSet fieldSet) throws BindException {
            Integer indexColumn = fileOption.getKeyColumn();
            String indexValue = fieldSet.readString(indexColumn);
            Guardian guardian = entityServise.findFirstByPersonalId(indexValue);

            if (guardian == null) {
                guardian = new Guardian();
            }
            bindData(guardian, fieldSet.getProperties());

            return guardian;
        }
    }

    @SuppressWarnings("unchecked")
    private class GuardianItemWriter implements ItemWriter<Guardian> {
        @Override
        public void write(List<? extends Guardian> items) throws Exception {
            List<Guardian> guardians = (List<Guardian>) items;
            for (int i = 0; i < guardians.size(); i++) {
                Guardian guardian = guardians.get(i);
                guardians.set(i, entityServise.save(guardian));
            }
        }
    }
}
