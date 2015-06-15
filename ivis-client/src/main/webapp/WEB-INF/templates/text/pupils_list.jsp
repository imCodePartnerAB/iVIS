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
<%@ page import="com.imcode.services.PupilService" %>
<%@ page import="com.imcode.entities.Pupil" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>

<%
    if (IvisOAuth2Utils.isTokenGood(request)) {
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        PupilService service = factory.getService(PupilService.class);
        List<Pupil> pupilList = null;
        try {
            pupilList = service.findAll();
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("pupils", pupilList);
    }
%>

<c:if test="${not empty pupils}">
    <h1>Pupils</h1>
    <table cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="ordered-by">Personal ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>School</th>
            <th>Class</th>
            <%--<th>&nbsp;</th>--%>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${pupils}" var="app">
            <tr data-application-id="${app.person.personalId}">
                <td>${app.person.personalId}</td>
                <td>${app.person.firstName}</td>
                <td>${app.person.lastName}</td>
                <td>${app.schoolClass.school}</td>
                <td>${app.schoolClass}</td>
                <td class="buttons">
                    <a class="button positive"
                       href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/pupils/edit?id=${app.id}">Details</a>

                    <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                          <%--method="post">--%>
                        <%--<button class="positive" type="submit">Approve</button>--%>
                        <%--<input type="hidden" name="status" value="approved"/>--%>
                    <%--</form>--%>
                    <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                          <%--method="post">--%>
                        <%--<button class="negative" type="submit">Decline</button>--%>
                        <%--<input type="hidden" name="status" value="declined"/>--%>
                    <%--</form>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
    <%--<div class="buttons">--%>
        <%--<a class="button positive"--%>
           <%--href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>--%>
    <%--</div>--%>
</c:if>
<jsp:include page="ivis_footer.jsp"/>