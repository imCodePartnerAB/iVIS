package com.imcode.services.csv;

import com.imcode.controllers.html.form.upload.FileOption;
import com.imcode.services.GenericService;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.ApplicationContext;

/**
 * Created by vitaly on 08.12.15.
 */
public abstract class CsvFieldSetMapper<T> implements FieldSetMapper<T> {
    public abstract  void setFileOption(FileOption fileOption);
    public abstract  void setService(GenericService<T, ?> service);
}
