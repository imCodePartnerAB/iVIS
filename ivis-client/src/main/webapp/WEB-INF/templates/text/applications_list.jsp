<%@ page import="com.imcode.entities.Person,
                 com.imcode.entities.Application" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.imcode.entities.embed.Decision" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>

<%
    request.setAttribute("statementStatusEnum", Decision.Status.values());
    //    DefaultIvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
//
    if (IvisOAuth2Utils.isTokenGood(request)) {
//        request.setAttribute("isAuthorized");
//    if (factory != null) {
//        ApplicationService service = factory.getStatementService();
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        ApplicationService service = factory.getService(ApplicationService.class);
//    List<Application> statements = null;
//    try {
//        statements = service.findAll();
////    } catch (UserRedirectRequiredException e) {
//    } catch (Exception e) {
//        IvisOAuth2Utils.setAccessToken(session, null);
//        response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));
//        return;
//    }
//    request.setAttribute("statements", statement);
        String searchText = request.getParameter("searchText");
        Decision.Status statusFilter = null;
        try {
            statusFilter = Decision.Status.valueOf(request.getParameter("statusFilter"));
        } catch (Exception ignore) { }

        List<Application> applicationList = null;
        try {
            List<Application> applications = service.findAll();
            applicationList = new LinkedList<Application>();

            for (Application statement : applications) {
                if (statusFilter != null && statement.getStatus() != statusFilter)
                    continue;

//                if (searchText != null && StringUtils.isNoneEmpty(searchText)) {
//                    Person pupilPerson = statement.getPupil() != null ? statement.getPupil().getPerson():null;
//                    Person submittedPerson = statement.getSubmittedPerson();
//                    if (!IvisOAuth2Utils.personContainsString(pupilPerson, searchText) && !IvisOAuth2Utils.personContainsString(submittedPerson, searchText))
//                        continue;
//                }

                applicationList.add(statement);
            }
            Collections.sort(applicationList, new Comparator<Application>() {
                @Override
                public int compare(Application o1, Application o2) {
                    return -Long.compare(o1.getId(), o2.getId());
                }
            });

        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("statements", applicationList);
    }
%>
<c:if test="${isAuthorized}">
<h1>Ansökningar</h1>

<form action="${clientAddress}" method="get">
    <div class="field">
        <label>Sök</label>
        <input type="text" name="searchText" value="${param.searchText}"/>
        <button class="positive" type="submit">Sök</button>
        <%--<button class="negative" type="button" onclick="ivis.ui.clearSerchText('searchText');">Clear search</button>--%>
    </div>

    <div class="field">
        <label>Filtrera</label>
        <select name="statusFilter">
            <%
                String statusFilter = request.getParameter("statusFilter");
                out.println("<option value=\"null\" " + ("null".equalsIgnoreCase(statusFilter)? "selected\"":"") + ">All</option>");

                for (Decision.Status statementStatus :Decision.Status.values()) {
                    out.println("<option value=\"" + statementStatus + "\" " + (statementStatus.toString().equalsIgnoreCase(statusFilter) ? "selected" : "") + ">" + StringUtils.capitalize(statementStatus.toString()) + "</option>");
                }

            %>
            <%--<option value="null" <c:if test="${not empty param.statusFilter and param.statusFilter ne 'null'}">selected</c:if>>All</option>--%>
                <%--<c:set var="enums" value="<%=Decision.Status.values()%>"/>--%>
            <%--<c:forEach var="enum" items="${enums}" varStatus="starus">--%>
                <%--<option value="${starus.current}" <c:if test="${not empty param.statusFilter and param.statusFilter eq starus.current}">selected</c:if>>${starus.current}</option>--%>
            <%--</c:forEach>--%>
        </select>
        <button class="positive" type="submit">Filtrera</button>
        <%--<button class="negative" type="button" onclick="ivis.ui.clearSerchText('statusFilter');">Clear filter</button>--%>
    </div>
</form>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th class="ordered-by">Id</th>
        <th>Skapad</th>
        <th>Uppdaterad</th>
        <th>Status</th>
        <th>Reg</th>
        <th>Av</th>
        <th>&nbsp;</th>
    </tr>
    </thead>

    <c:if test="${not empty statements}">
        <tbody>
        <c:forEach items="${statements}" var="app">
            <tr data-application-id="${app.id}">
                <td>${app.id}</td>
                <fmt:formatDate value="${app.createDate}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>
                <td>${dateString}</td>
                <fmt:formatDate value="${app.createDate}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>
                <td>${dateString}</td>
                <td>${app.status.description}</td>
                <td>${app.registrationNumber}</td>
                <td>${app.submittedUser}</td>
                <td class="buttons">
                    <a class="button positive"
                       href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${app.id}">Visa</a>

                    <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                          <%--method="get">--%>
                        <%--<button class="positive" type="submit">Approve</button>--%>
                        <%--<input type="hidden" name="status" value="APPROVE"/>--%>
                    <%--</form>--%>
                    <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                          <%--method="get">--%>
                        <%--<button class="negative" type="submit">Decline</button>--%>
                        <%--<input type="hidden" name="status" value="DENI"/>--%>
                    <%--</form>--%>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </c:if>
</table>
<div class="buttons">
    <a class="button positive"
       href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>
</div>
</c:if>
<jsp:include page="ivis_footer.jsp"/>