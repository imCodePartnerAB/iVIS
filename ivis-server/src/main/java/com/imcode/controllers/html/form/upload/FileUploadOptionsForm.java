package com.imcode.controllers.html.form.upload;

/**
 * Created by vitaly on 02.12.15.
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class FileUploadOptionsForm implements Serializable{

    private List<FileOption> fileOptionList;

    //Getter and setter methods

    public List<FileOption> getFileOptionList() {
        return fileOptionList;
    }

    public void setFileOptionList(List<FileOption> fileOptionList) {
        this.fileOptionList = fileOptionList;
    }
}