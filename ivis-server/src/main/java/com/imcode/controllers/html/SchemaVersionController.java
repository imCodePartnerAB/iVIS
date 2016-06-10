package com.imcode.controllers.html;

import com.imcode.services.SchemaVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by ruslan on 09.06.16.
 */
@Controller
@RequestMapping("/database")
public class SchemaVersionController {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private SchemaVersionService schemeVersionService;

    @RequestMapping(method = RequestMethod.POST)
    public String migrate() {

        return "/";
    }

}
