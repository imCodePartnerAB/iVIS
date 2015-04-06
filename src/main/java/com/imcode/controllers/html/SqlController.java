package com.imcode.controllers.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by vitaly on 06.04.15.
 */
@Controller
@RequestMapping("/sql")
public class SqlController {
    @Autowired
    private DataSource dataSource;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showform(WebRequest webRequest) {
        return "sql/show";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String execute(@RequestParam("sql") String sql, Model model, WebRequest webRequest) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> resultList = null;
        try {
            resultList = jdbcTemplate.queryForList(sql);
            model.addAttribute("results", resultList);
        } catch (DataAccessException e) {
            model.addAttribute("message", "Error:" + e.getMessage());
        }

        return "sql/show";
    }
}
