<%@ page import="com.imcode.entities.Person,
                 com.imcode.entities.Pupil" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.services.PupilService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>

<%
    if (IvisOAuth2Utils.isTokenGood(request)) {
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        PupilService service = factory.getService(PupilService.class);
        List<Pupil> pupilList = new LinkedList<Pupil>();
        String searchText = request.getParameter("searchText");
        try {
            List<Pupil> pupils = service.findAll();
            if (searchText != null && StringUtils.isNotEmpty(searchText)) {
//                request.setAttribute("searchText", searchText);
//                CharSequence sequence =
                for (Pupil pupil : pupils) {
                    Person person = pupil.getPerson();
                    if (IvisOAuth2Utils.personContainsString(person, searchText)) {
                        pupilList.add(pupil);
                    }
                }
            } else {
                pupilList = pupils;
            }
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("pupilList", pupilList);
    }
%>

<h1>Elever</h1>
<form action="${clientAddress}/pupils" method="get">
    <div class="field">
        <label>Sök</label>
        <input type="text" name="searchText" value="${param.searchText}"/>
        <%--<div class="buttons">--%>
        <div class="buttons inline">
            <button class="positive button" type="submit">Sök</button>
        </div>
        <%--<button class="negative" type="button" onclick="ivis.ui.clearSerchText('searchText');">Clear search</button>--%>
        <%--</div>--%>
    </div>
</form>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th class="ordered-by">Person-nr</th>
        <th>Förnamn</th>
        <th>Efternamn</th>
        <th>Skola</th>
        <th>Klass</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:if test="${not empty pupilList}">
        <tbody>
        <c:forEach items="${pupilList}" var="app">
            <tr data-application-id="${app.person.personalId}">
                <td>${app.person.personalId}</td>
                <td>${app.person.firstName}</td>
                <td>${app.person.lastName}</td>
                <td>${app.school}</td>
                <td>${app.schoolClass}</td>
                <td class="buttons">
                    <%--<a class="button positive" href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/pupils/edit?id=${app.id}">Edit</a>--%>

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
    </c:if>
</table>
<%--<div class="buttons">--%>
<%--<a class="button positive"--%>
<%--href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>--%>
<%--</div>--%>

<jsp:include page="ivis_footer.jsp"/>