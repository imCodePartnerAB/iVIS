<%@ page import="com.imcode.entities.Statement,
                 com.imcode.services.StatementService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>
<%
    if (IvisOAuth2Utils.isTokenGood(request)) {
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        StatementService service = factory.getService(StatementService.class);
        Statement statement = null;
        try {
            statement = service.find(Long.valueOf(request.getParameter("id")));
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("statement", statement);
    }
%>
<h1>Application</h1>
<c:if test="${not empty statement}">
    <table cellpadding="0" cellspacing="0">
        <tbody>
        <tr>
            <td>Id</td>
            <td>${statement.id}</td>
        </tr>
        <tr>
            <td>Submit date</td>
            <td>${statement.submitDate}</td>
        </tr>
        <tr>
            <td>Change date</td>
            <td>${statement.changedDate}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${statement.status}</td>
        </tr>
        <tr>
            <td>Submitted person</td>
            <td>${statement.submittedPerson}</td>
        </tr>
        <tr>
            <td>Pupil</td>
            <td>${statement.pupil}</td>
        </tr>
        </tbody>
    </table>
    <div class="buttons">
        <a class="button neutral" href="<%=Imcms.getServerProperties().getProperty("StatementsAddress")%>">Back</a>
    </div>
</c:if>
<jsp:include page="ivis_footer.jsp"/>