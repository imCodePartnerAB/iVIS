package com.imcode.utils;

import com.imcode.entities.SchemaVersion;
import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by ruslan on 09.06.16.
 */
public class DatabaseWorker {
    private BasicDataSource dataSource;
    private SchemaVersion schemaVersion;
    private ServletContext servletContext;

    private enum TypeSQL {
        SCRIPT("script"),
        DUMP("dump");
        private String typeSQL;
        private TypeSQL(String typeSQL) {
            this.typeSQL = typeSQL;
        }
        @Override
        public String toString(){
            return typeSQL;
        }
    }

    public DatabaseWorker(BasicDataSource dataSource, SchemaVersion schemaVersion, ServletContext servletContext) {
        this.dataSource = dataSource;
        this.schemaVersion = schemaVersion;
        this.servletContext = servletContext;
    }

    public boolean runScript(MultipartFile multipartFile) {
        String fileName = genFileNametOf(schemaVersion, TypeSQL.SCRIPT);
        saveScriptToFile(multipartFile, fileName);

        return execute(fileName, TypeSQL.SCRIPT);
    }

    public void createVersionDump() {
        String fileName = genFileNametOf(schemaVersion, TypeSQL.DUMP);

        File file = new File(fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        execute(fileName, TypeSQL.DUMP);
    }

    public void migrateToVersion() {
        String fileName = genFileNametOf(schemaVersion, TypeSQL.DUMP);

        dropCreateDB();

        execute(fileName, TypeSQL.SCRIPT);

    }

    public void saveVersionDumpInResponse(HttpServletResponse httpServletResponse) {
        boolean needDeleteTemp = false;
        if (schemaVersion == null) {
            schemaVersion = new SchemaVersion();
            schemaVersion.setVersion("temp");
            needDeleteTemp = true;
            createVersionDump();
        }

        String fileName = genFileNametOf(schemaVersion, TypeSQL.DUMP);

        File initialFile = new File(fileName);

        httpServletResponse.setContentType("application/force-download");
        httpServletResponse.setContentLength((int) initialFile.length());
        httpServletResponse.setHeader("Content-Transfer-Encoding", "binary");
        httpServletResponse.setHeader("Content-Disposition","attachment; filename=\"" + initialFile.getName() + "\"");

        InputStream targetStream = null;

        try {
            targetStream = new BufferedInputStream(new FileInputStream(initialFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileCopyUtils.copy(targetStream, httpServletResponse.getOutputStream());
            httpServletResponse.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (needDeleteTemp) {
            initialFile.delete();
        }


    }

    public void deleteVersion() {
        String fileName = genFileNametOf(schemaVersion, TypeSQL.SCRIPT);
        String path = fileName.substring(0, fileName.lastIndexOf('/'));
        try {
            FileUtils.deleteDirectory(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean execute(String fileName, TypeSQL type) {
        String url = dataSource.getUrl();
        String dbName = url.substring(url.lastIndexOf('/') + 1, url.lastIndexOf('?'));

        StringBuilder command = new StringBuilder(type.equals(TypeSQL.SCRIPT) ? "mysql" : "mysqldump");
        command.append(" -u ");
        command.append(dataSource.getUsername());
        command.append(" -p");
        command.append(dataSource.getPassword());
        command.append(" ");
        command.append(dbName);
        command.append(type.equals(TypeSQL.SCRIPT) ? " < " : " > ");
        command.append(fileName);

        return StaticUtls.executeCmd(command.toString());
    }

    private void saveScriptToFile(MultipartFile multipartFile, String fileName) {
        File script = new File(fileName);

        script.getParentFile().mkdirs();

        try {
            script.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            multipartFile.transferTo(script);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String genFileNametOf(SchemaVersion schemaVersion, TypeSQL type) {
        String version = schemaVersion.getVersion();
        StringBuilder path = new StringBuilder(servletContext.getRealPath("/WEB-INF/"));
        path.append("schema-versions/");
        path.append(version + "/");
        path.append(type + "_" + version + ".sql");
        return path.toString();
    }

    private void dropCreateDB() {
        String url = dataSource.getUrl();
        String dbName = url.substring(url.lastIndexOf("/") + 1, url.length());

        StringBuilder path = new StringBuilder(servletContext.getRealPath("/WEB-INF/"));
        path.append("schema-versions/");
        path.append("temp/");
        path.append("createDrop.sql");

        File file = new File(path.toString());

        if (!file.exists()) {
            file.getParentFile().mkdirs();

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(file));
                bw.write("DROP DATABASE IF EXISTS " + dbName + ";");
                bw.write("CREATE SCHEMA " + dbName + ";");
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        execute(file.getAbsolutePath(), TypeSQL.SCRIPT);

    }




}
