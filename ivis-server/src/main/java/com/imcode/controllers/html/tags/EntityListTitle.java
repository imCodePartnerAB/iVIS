package com.imcode.controllers.html.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by vitaly on 15.07.15.
 */
public class EntityListTitle extends TagSupport {
    //    public static final String TITLE_SUFIX = "list";
//    public static final String DEFAULT_TITLE = "Entity " + TITLE_SUFIX;
    private String title;

    @Override
    public int doStartTag() throws JspException {
        StringBuilder sb = new StringBuilder("<div>")
                .append("<h1>")
                .append(title)
                .append("</h1>")
                .append("</div>");
        try {
            pageContext.getOut().print(sb.toString());
        } catch (IOException ioException) {
            throw new JspException("Error: " + ioException.getMessage());
        }
        return SKIP_BODY;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
