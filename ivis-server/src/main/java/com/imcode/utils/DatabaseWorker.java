package com.imcode.utils;

import com.imcode.services.SchemaVersionService;

import javax.activation.DataSource;

/**
 * Created by ruslan on 09.06.16.
 */
public class DatabaseWorker {
    private DataSource dataSource;

    private SchemaVersionService schemaVersionService;

    public DatabaseWorker(DataSource dataSource, SchemaVersionService schemaVersionService) {
        this.dataSource = dataSource;
        this.schemaVersionService = schemaVersionService;
    }





}
