package imcode.tags;


import imcode.services.utils.IvisOAuth2Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthorizedTag extends TagSupport {

    private String roles;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = null;
        //if request is not HttpServletRequest than permit body
        try {
            request = (HttpServletRequest) pageContext.getRequest();
        } catch (ClassCastException e) {
            return EVAL_BODY_INCLUDE;
        }

        if (!IvisOAuth2Utils.isTokenGood(request)) { // if token no good than skip
            return SKIP_BODY;
        } else if (roles == null){ // if token good, but you don't need use roles based
            return EVAL_BODY_INCLUDE;
        } else if (IvisOAuth2Utils.getIvisLoggedInUser(request).hasRoles(roles.split(","))){ // if you need use roles based
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;

    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
