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
<%@ page import="org.springframework.security.oauth2.common.util.OAuth2Utils" %>
<%@ page import="imcode.services.IvisServiceFactory" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>

<%
//    DefaultIvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
//
    if (IvisOAuth2Utils.isTokenGood(request)) {
//    if (factory != null) {
//        StatementService service = factory.getStatementService();
    IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
    StatementService service = factory.getService(StatementService.class);
//    List<Statement> statements = null;
//    try {
//        statements = service.findAll();
////    } catch (UserRedirectRequiredException e) {
//    } catch (Exception e) {
//        IvisOAuth2Utils.setAccessToken(session, null);
//        response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));
//        return;
//    }
//    request.setAttribute("statements", statement);
        List<Statement> statementList = null;
        try {
            statementList = service.findAll();
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("statements", statementList);
    }
%>

<c:if test="${not empty statements}">
    <h1>Applications</h1>
    <table cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="ordered-by">Id</th>
            <th>SubmitDate</th>
            <th>ChangeDate</th>
            <th>Status</th>
            <th>&nbsp;</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${statements}" var="app">
            <tr data-application-id="${app.id}">
                <td>${app.id}</td>
                <td>${app.submitDate}</td>
                <td>${app.changedDate}</td>
                <td>${app.status}</td>
                <td class="buttons">
                    <a class="button positive"
                       href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/details.jsp?id=${app.id}">Details</a>

                    <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                          method="post">
                        <button class="positive" type="submit">Approve</button>
                        <input type="hidden" name="status" value="approved"/>
                    </form>
                    <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                          method="post">
                        <button class="negative" type="submit">Decline</button>
                        <input type="hidden" name="status" value="declined"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <div class="buttons">
        <a class="button positive"
           href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>
    </div>
</c:if>
<jsp:include page="ivis_footer.jsp"/>