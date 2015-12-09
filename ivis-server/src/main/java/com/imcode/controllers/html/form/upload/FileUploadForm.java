package com.imcode.controllers.html.form.upload;

/**
 * Created by vitaly on 02.12.15.
 */
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

    private List<MultipartFile> files;

    //Getter and setter methods
    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}