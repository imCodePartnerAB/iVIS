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
import java.time.OffsetTime;
import java.util.*;

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
    public String execute(@RequestParam("sql") String sqlString, Model model, WebRequest webRequest) {
        String[] sqls = sqlString.split(";");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map> resultList = new LinkedList<>();

        for (String sql : sqls) {
            sql = sql.trim();
            List<Map<String, Object>> result = null;
            Integer rowCount = null;
            String errorMessage = null;

            if (sql.isEmpty()) continue;

            try {
                if ("select".equalsIgnoreCase(sql.substring(0, 6))) {
                    result = jdbcTemplate.queryForList(sql);
                    rowCount = result.size();
                } else {
                    rowCount = jdbcTemplate.update(sql);
                }
            } catch (Exception e) {
                errorMessage = e.getMessage();
            }
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("rowCount", rowCount);
            resultMap.put("result", result);
            resultMap.put("errorMessage", errorMessage);

            resultList.add(resultMap);
        }

        model.addAttribute("resultList", resultList);
        model.addAttribute("sqlString", sqlString);

        return "sql/show";
    }
}
