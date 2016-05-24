package com.imcode.utils;

import com.imcode.entities.Activity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Properties;

/**
 * Created by ruslan on 5/12/16.
 */
public class IssueAttachmentFileUtil {


    public void saveActivityAttachment(Activity activity, MultipartFile attachment, ServletContext servletContext) {
        String filePath = generateFilePath(activity, servletContext);

        File dir = new File(filePath.substring(0, filePath.lastIndexOf("/")));

        if (!dir.exists())
            dir.mkdirs();

        File serverFile = new File(filePath);

        if (!serverFile.exists()) {
            try {
                serverFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            attachment.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void saveActivityAttachmentInResponse(Activity activity, HttpServletResponse response, ServletContext servletContext) {

        String filePath = generateFilePath(activity, servletContext);

        File initialFile = new File(filePath);

        response.setContentType("application/force-download");
        response.setContentLength((int) initialFile.length());
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition","attachment; filename=\"" + activity.getFileName() + "\"");

        InputStream targetStream = null;

        try {
            targetStream = new BufferedInputStream(new FileInputStream(initialFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileCopyUtils.copy(targetStream, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String generateFilePath(Activity activity, ServletContext servletContext) {
        StringBuilder path = new StringBuilder(servletContext.getRealPath("/WEB-INF/"));

        path.append("issues/");

        path.append(activity.getIssue().getId());

        path.append("/activities/");

        path.append(activity.getId() + "/");

        path.append(activity.getFileName());

        return path.toString();
    }


    public void deleteIfExcist(Activity activity, ServletContext servletContext) {
        String fileName = activity.getFileName();
        if (fileName != null) {
            String filePath = generateFilePath(activity, servletContext);
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
