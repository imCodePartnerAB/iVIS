package com.imcode.controllers.html.form.upload;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.*;

/**
 * Created by vitaly on 01.12.15.
 */
public class FileOption  implements Serializable {
    private static final int DEFAULT_PREVIEW_ROW_COUNT = 10;

    private int previewRowsCount = DEFAULT_PREVIEW_ROW_COUNT;
    private Class<?> type;
    private String fileId;
    private String originalFileName;
    private int columnCount;
    private int skipRows = 0;
    private Collection<String[]> firstRows = new ArrayList<>(previewRowsCount);
    private List<String> columnNameList = new ArrayList<>();
    private boolean firstRowSkip = false;
    private Integer keyColumn;


    public FileOption() {
    }

    public static FileOption of(String fileId, MultipartFile file) {
        return of(fileId, file, DEFAULT_PREVIEW_ROW_COUNT);
    }

    public static FileOption of(String fileId, MultipartFile file, int previewRowsCount) {
        FileOption instance = new FileOption();
        instance.previewRowsCount = previewRowsCount;
        instance.fileId = fileId;
        instance.originalFileName = file.getOriginalFilename();
        instance.fillPreviewLines(file);
        instance.columnNameList = Arrays.asList(new String[instance.columnCount]);
        return instance;
    }

    protected void fillPreviewLines(MultipartFile file) {
        try {
            Resource resource = new InputStreamResource(file.getInputStream());
            DefaultLineMapper<String[]> lineMapper = new DefaultLineMapper<>();
            lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
            lineMapper.setFieldSetMapper(FieldSet::getValues);

            FlatFileItemReader<String[]> itemReader = new FlatFileItemReader<>();
            itemReader.setResource(resource);
            itemReader.setLineMapper(lineMapper);
            itemReader.open(new ExecutionContext());

            for (int i = 0; i < 10; i++) {
                String[] row = itemReader.read();
                if (row == null) {
                    break;
                }

                firstRows.add(row);
                columnCount = Math.max(columnCount, row.length);
            }

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getSkipRows() {
        return skipRows;
    }

    public void setSkipRows(int skipRows) {
        this.skipRows = skipRows;
    }

    public Collection<String[]> getFirstRows() {
        return firstRows;
    }

    public void setFirstRows(Collection<String[]> firstRows) {
        this.firstRows = firstRows;
    }

    public List<String> getColumnNameList() {
        return columnNameList;
    }

    public void setColumnNameList(List<String> columnNameList) {
        this.columnNameList = columnNameList;
    }

    public boolean isFirstRowSkip() {
        return firstRowSkip;
    }

    public void setFirstRowSkip(boolean firstRowSkip) {
        this.firstRowSkip = firstRowSkip;
    }

    public Integer getKeyColumn() {
        return keyColumn;
    }

    public void setKeyColumn(Integer keyColumn) {
        this.keyColumn = keyColumn;
    }
}
