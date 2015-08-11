package com.imcode.controllers.html.tags;

import com.imcode.entities.Role;
import com.imcode.entities.User;
import com.imcode.entities.interfaces.JpaEntity;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by vitaly on 16.07.15.
 */
public class EntityListTable extends BodyTagSupport {
    private Iterable<JpaEntity> entities;
    private static final String DEFAULT_DELIMETERS = ",.; ";
    private Iterator<JpaEntity> entityIterator;
    private String columns;
    private Set<String> columnSet;

    @Override
    public int doStartTag() throws JspException {
        entityIterator = entities.iterator();
        String[] strings = columns.split("[" + DEFAULT_DELIMETERS + "]+");

        this.columnSet = new LinkedHashSet<>();

        StringBuilder sb = new StringBuilder()
                .append("<div>")
                .append("<table cellpadding=\"0\" cellspacing=\"0\">")
                .append("<thead>").append("</tr>");

        for (String columnName : strings) {
            this.columnSet.add(columnName);
            sb.append("<th>")
                    .append(columnName.toUpperCase())
                    .append("</th>");
        }
        sb.append("<th>&nbsp;</th>");
        sb.append("</tr>").append("</thead>");

        while (entityIterator.hasNext()) {
            JpaEntity entity = entityIterator.next();
            sb.append("<tr>");

            for (String columnName : columnSet) {
                sb.append("<td>");
                Object value = getFieldValue(entity, columnName);
                sb.append(value);
                sb.append("</td>");
            }
            sb.append("<td class=\"buttons\">")
                    .append("<a class=\"button positive\" href=\"" + entity.getClass().getSimpleName() + "/" + entity.getId() + "?form\">Edit</a>")
                    .append("<button class=\"negative\" type=\"button\" onclick=\"deleteElement('/" + entity.getClass().getSimpleName() + "'," + entity.getId() + ")\">Remove</button>")
                    .append("</td>");

            sb.append("</tr>");

//            try {
//                pageContext.getOut().println(sb.toString());
//            } catch (IOException e) {
//                throw new JspTagException(e.getMessage());
//            }
        }

        try {
            pageContext.getOut().write(sb.toString());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (entityIterator.hasNext()) {
            JpaEntity entity = entityIterator.next();
            StringBuilder sb = new StringBuilder("<tr>");

            for (String columnName : columnSet) {
                sb.append("<td>");
                Object value = getFieldValue(entity, columnName);
                sb.append(value);
                sb.append("</td>");
            }

            sb.append("</tr>");

            try {
                pageContext.getOut().println(sb.toString());
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        StringBuilder sb = new StringBuilder("</table>")
                .append("</div>");
        try {
            pageContext.getOut().write(sb.toString());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public Iterable<JpaEntity> getEntities() {
        return entities;
    }

    public void setEntities(Iterable<JpaEntity> entities) {
        this.entities = entities;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    protected static Object getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Class<?> clazz = obj.getClass();

        Field field = org.springframework.util.ReflectionUtils.findField(clazz, fieldName);

        if (field == null) {
            throw new RuntimeException("There is no field \"" + fieldName + "\" in class \"" + clazz + "\"");
        }

        org.springframework.util.ReflectionUtils.makeAccessible(field);

        try {
            result = field.get(obj);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static void main(String[] args) {
//        User  user = new User("Vitaly", "415263", new Role("RoleAdmin"), new Role("RoleUser"));
//        User  user2 = new User("Vitaly1", "4152631", new Role("RoleAdmin"), new Role("RoleUser"));
//        EntityListTable entityListTable = new EntityListTable();
//        entityListTable.setColumns("username,password,enabled");
//        entityListTable.setEntities(Arrays.asList(user, user2));

//        System.out.println(EntityListTable.getFieldValue(user, "username"));
    }

}
