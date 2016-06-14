package com.imcode.controllers.html;

import com.imcode.entities.SchemaVersion;
import com.imcode.services.SchemaVersionService;
import com.imcode.utils.DatabaseWorker;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<SchemaVersion> getAllSchemaVersion(WebRequest webRequest) {
        return schemeVersionService.findAll();
    }

    @RequestMapping(value = "/current/{version}", method = RequestMethod.POST)
    public @ResponseBody SchemaVersion migrateToVersion(@PathVariable("version") String version, WebRequest webRequest) {
        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);

        if (schemaVersion.getCurrent()) {
            return schemaVersion;
        }

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);

        databaseWorker.migrateToVersion();

        SchemaVersion currentVersion = schemeVersionService.findCurrentVersion();
        currentVersion.setCurrent(false);
        schemeVersionService.save(currentVersion);

        schemaVersion.setCurrent(true);
        schemeVersionService.save(schemaVersion);

        return schemaVersion;
    }

    @RequestMapping(value = "/dump/{version}", method = RequestMethod.GET)
    public ModelAndView getVersionDump(@PathVariable("version") String version,
                               HttpServletResponse httpServletResponse,
                               WebRequest webRequest) {
        SchemaVersion schemaVersion = schemeVersionService.findVersion(version);

        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, schemaVersion, servletContext);

        databaseWorker.saveVersionDumpInResponse(httpServletResponse);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/dump", method = RequestMethod.GET)
    public ModelAndView getVersionDump(HttpServletResponse httpServletResponse,
                                       WebRequest webRequest) {
        DatabaseWorker databaseWorker = new DatabaseWorker(dataSource, null, servletContext);

        databaseWorker.saveVersionDumpInResponse(httpServletResponse);

        return new ModelAndView("redirect:/test.html");
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
