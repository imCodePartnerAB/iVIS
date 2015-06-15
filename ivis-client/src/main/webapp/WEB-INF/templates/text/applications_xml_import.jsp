<%@ page import="com.imcode.entities.Statement,
                 com.imcode.services.StatementService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.restful.IvisFacade" %>
<%@ page import="imcode.services.restful.DefaultIvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.oro.text.perl.Perl5Util" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails" %>
<%@ page import="org.springframework.security.oauth2.common.OAuth2AccessToken" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="org.springframework.security.oauth2.client.OAuth2ClientContext" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>
<h1>Import</h1>
<c:if test="${message != null}">
    <div class="field">
            ${message.message}
    </div>
</c:if>
<form method="post" enctype="multipart/form-data" name="xmlForm"
      action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/xml">
    <div class="field">
        <label>XML to import</label>
        <input type="file" name="file"/>
        <%--<textarea cols="60" rows="8" name="body" onkeyup="k(event);">${body}</textarea>--%>
    </div>
    <div class="buttons">
        <button class="positive" type="submit">Import</button>
        <button class="negative" type="reset">Reset</button>
    </div>
</form>
<jsp:include page="ivis_footer.jsp"/>