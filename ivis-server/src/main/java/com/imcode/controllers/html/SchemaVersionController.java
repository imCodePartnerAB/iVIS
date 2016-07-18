package com.imcode.controllers.html;

import com.imcode.entities.SchemaVersion;
import com.imcode.services.SchemaVersionService;
import com.imcode.utils.DatabaseWorker;
import com.imcode.utils.StaticUtls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ruslan on 09.06.16.
 */
@Controller
@RequestMapping("/schema_versions")
public class SchemaVersionController {

    @Autowired
    @Qualifier("dataSource")
    private BasicDataSource dataSource;

    @Autowired
    private SchemaVersionService schemeVersionService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView migrate(@RequestParam("file") CommonsMultipartFile multipartFile,
                                @RequestParam(value = "description", required = false) String description,
                                WebRequest webRequest) {

        SchemaVersion currentVersion = createCurrentVersion(multipartFile.getOriginalFilename(), description);

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, currentVersion, servletContext);

        if (!databaseWorker.runScript(multipartFile)) {
            schemeVersionService.delete(currentVersion.getId());
        } else {
            databaseWorker.createVersionDump();
        }

        return new ModelAndView("redirect:/test.html");
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public @ResponseBody SchemaVersion getCurrentSchemaVersion(WebRequest webRequest) {
        return schemeVersionService.findCurrentVersion();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<SchemaVersion> getAllSchemaVersion(WebRequest webRequest) {
        return schemeVersionService.findAll();
    }

    @RequestMapping(value = "/access", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getDataAccess(WebRequest webRequest) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("URL", dataSource.getUrl());
        objectMap.put("LOGIN", dataSource.getUsername());
        objectMap.put("PASSWORD", dataSource.getPassword());
        return objectMap;
    }

    @RequestMapping(value = "/current/{version}", method = RequestMethod.POST)
    public @ResponseBody SchemaVersion migrateToVersion(@PathVariable("version") String version, WebRequest webRequest) {
        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);
        if (schemaVersion.getCurrent()) {
            return schemaVersion;
        }

        SchemaVersion currentVersion = schemeVersionService.findCurrentVersion();
        currentVersion.setCurrent(false);
        schemeVersionService.save(currentVersion);

        schemaVersion.setCurrent(true);
        schemeVersionService.save(schemaVersion);

        List<SchemaVersion> schemaVersionList = schemeVersionService.findAll();

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);

        databaseWorker.migrateToVersion();

        schemeVersionService.findAll().stream()
                .forEach(schema -> schemeVersionService.delete(schema.getId()));

        schemeVersionService.save(schemaVersionList);

        return schemaVersion;
    }

    @RequestMapping(value = "/dump/{version}", method = RequestMethod.GET)
    public void getVersionDump(@PathVariable("version") String version,
                               HttpServletResponse httpServletResponse,
                               WebRequest webRequest) {
        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);

        databaseWorker.saveVersionDumpInResponse(httpServletResponse);

//        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/script/{version}", method = RequestMethod.GET)
    public void getVersionScript(@PathVariable("version") String version,
                               HttpServletResponse httpServletResponse,
                               WebRequest webRequest) {
        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);

        databaseWorker.saveVersionScriptInResponse(httpServletResponse);

//        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/dump", method = RequestMethod.GET)
    public void getVersionDump(HttpServletResponse httpServletResponse,
                                       WebRequest webRequest) {
        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, null, servletContext);

        databaseWorker.saveVersionDumpInResponse(httpServletResponse);

//        return new ModelAndView("redirect:/test.html");
    }

    @RequestMapping(value = "/delete/{version}", method = RequestMethod.POST)
    public ModelAndView deleteSchemaVersion(@PathVariable("version") String version,
                                            WebRequest webRequest) {

        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);
        if (schemaVersion.getCurrent()) {
            return new ModelAndView("redirect:/test.html");
        }

        schemeVersionService.delete(schemaVersion.getId());

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);
        databaseWorker.deleteVersion();

        return new ModelAndView("redirect:/test.html");
    }

    @RequestMapping(value = "/command", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> executeCommand(HttpServletRequest request, WebRequest webRequest) {

        String cmd = request.getParameter("cmd");
        String config = request.getParameter("config");

        Process process = StaticUtls.executeCmdConfig(cmd, config);

        Map<String, Object> response = new HashMap<>();

        response.put("exit code", process.exitValue());

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));

        StringBuffer output = new StringBuffer();

        try {
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.put("output", output.toString());

        reader =
                new BufferedReader(new InputStreamReader(process.getErrorStream()));

        output = new StringBuffer();

        try {
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.put("error", output.toString());

        return response;
    }

    private SchemaVersion createCurrentVersion(String versionName, String description) {
        SchemaVersion currentVersion = schemeVersionService.findCurrentVersion();
        if (currentVersion != null) {
            currentVersion.setCurrent(false);
            schemeVersionService.save(currentVersion);
        }

        SchemaVersion schemaVersion = new SchemaVersion();
        schemaVersion.setCurrent(true);
        schemaVersion.setVersion(versionName.substring(0, versionName.indexOf(".sql")));
        schemaVersion.setTimestamp(new Date());
        if (description != null) {
            schemaVersion.setDescription(description);
        }

        return schemeVersionService.save(schemaVersion);
    }

}
