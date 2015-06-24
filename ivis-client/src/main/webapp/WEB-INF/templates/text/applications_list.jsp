<%@ page import="com.imcode.entities.Person,
                 com.imcode.entities.Statement" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.entities.enums.StatementStatus" %>
<%@ page import="com.imcode.services.StatementService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>

<%
    request.setAttribute("statementStatusEnum", StatementStatus.values());
    //    DefaultIvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
//
    if (IvisOAuth2Utils.isTokenGood(request)) {
//        request.setAttribute("isAuthorized");
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
        String searchText = request.getParameter("searchText");
        StatementStatus statusFilter = null;
        try {
            statusFilter = StatementStatus.valueOf(request.getParameter("statusFilter"));
        } catch (Exception ignore) { }

        List<Statement> statementList = null;
        try {
            List<Statement> statements = service.findAll();
            statementList = new LinkedList<Statement>();

            for (Statement statement :statements) {
                if (statusFilter != null && statement.getStatus() != statusFilter)
                    continue;

                if (searchText != null && StringUtils.isNoneEmpty(searchText)) {
                    Person pupilPerson = statement.getPupil() != null ? statement.getPupil().getPerson():null;
                    Person submittedPerson = statement.getSubmittedPerson();
                    if (!IvisOAuth2Utils.personContainsString(pupilPerson, searchText) && !IvisOAuth2Utils.personContainsString(submittedPerson, searchText))
                        continue;
                }

                statementList.add(statement);
            }


        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        request.setAttribute("statements", statementList);
    }
%>
<c:if test="${isAuthorized}">
<h1>Applications</h1>

<form action="${clientAddress}" method="get">
    <div class="field">
        <label>Search</label>
        <input type="text" name="searchText" value="${param.searchText}"/>
        <button class="positive" type="submit">Search</button>
        <button class="negative" type="button" onclick="ivis.ui.clearSerchText('searchText');">Clear search</button>
    </div>

    <div class="field">
        <label>Filter on status</label>
        <select name="statusFilter">
            <%
                String statusFilter = request.getParameter("statusFilter");
                out.println("<option value=\"null\" " + ("null".equalsIgnoreCase(statusFilter)? "selected\"":"") + ">All</option>");

                for (StatementStatus statementStatus :StatementStatus.values()) {
                    out.println("<option value=\"" + statementStatus + "\" " + (statementStatus.toString().equalsIgnoreCase(statusFilter) ? "selected" : "") + ">" + StringUtils.capitalize(statementStatus.toString()) + "</option>");
                }

            %>
            <%--<option value="null" <c:if test="${not empty param.statusFilter and param.statusFilter ne 'null'}">selected</c:if>>All</option>--%>
                <%--<c:set var="enums" value="<%=StatementStatus.values()%>"/>--%>
            <%--<c:forEach var="enum" items="${enums}" varStatus="starus">--%>
                <%--<option value="${starus.current}" <c:if test="${not empty param.statusFilter and param.statusFilter eq starus.current}">selected</c:if>>${starus.current}</option>--%>
            <%--</c:forEach>--%>
        </select>
        <button class="positive" type="submit">Filter</button>
        <button class="negative" type="button" onclick="ivis.ui.clearSerchText('statusFilter');">Clear filter</button>
    </div>
</form>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th class="ordered-by">Id</th>
        <th>SubmitDate</th>
        <th>ChangeDate</th>
        <th>Status</th>
        <th>Student</th>
        <th>Handled by</th>
        <th>&nbsp;</th>
    </tr>
    </thead>

    <c:if test="${not empty statements}">
        <tbody>
        <c:forEach items="${statements}" var="app">
            <tr data-application-id="${app.id}">
                <td>${app.id}</td>
                <td>${app.submitDate}</td>
                <td>${app.changedDate}</td>
                <td>${app.status}</td>
                <td>${app.pupil}</td>
                <td>${app.submittedPerson}</td>
                <td class="buttons">
                    <a class="button positive"
                       href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/details.jsp?id=${app.id}">Details</a>

                    <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                          method="get">
                        <button class="positive" type="submit">Approve</button>
                        <input type="hidden" name="status" value="approved"/>
                    </form>
                    <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                          method="get">
                        <button class="negative" type="submit">Decline</button>
                        <input type="hidden" name="status" value="declined"/>
                    </form>
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