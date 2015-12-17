<%@ page import="com.imcode.entities.Guardian,
                 com.imcode.services.GuardianService" pageEncoding="UTF-8" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.List" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<imcms:variables/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

    <title><c:out value="${document.headline}"/> - Powered by imCMS from imCode Partner AB</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/popup.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/general.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"></script>
</head>
<body>

<div class="container">
    <div class="content">
        <div class="box">

            <%
//                request.setAttribute("statementStatusEnum", StatementStatus.values());
                List<Guardian> guardians = null;
                if (IvisOAuth2Utils.isTokenGood(request)) {
                    request.setAttribute("isAuthorized", true);
                    IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
                    GuardianService service = factory.getService(GuardianService.class);
                    try {
                        guardians = service.findAll();
                    } catch (UserRedirectRequiredException e) {
                        request.setAttribute("isAutorizen", true);
                    }
                    request.setAttribute("guardians", guardians);
                }
            %>
            <c:if test="${isAuthorized}">

            <h1>Guardians</h1>
            <table cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th class="ordered-by">Id</th>
                    <th>Person</th>
                    <%--<th>ChangeDate</th>--%>
                    <%--<th>Status</th>--%>
                    <%--<th>Student</th>--%>
                    <%--<th>Handled by</th>--%>
                    <%--<th>&nbsp;</th>--%>
                </tr>
                </thead>

                <c:if test="${not empty guardians}">
                    <tbody>
                    <c:forEach items="${guardians}" var="app">
                        <tr data-application-id="${app.id}" onclick="ivis.ui.selectRow(${app.id});">
                            <td>${app.id}</td>
                            <td>${app.person}</td>
                            <%--<td>${app.changedDate}</td>--%>
                            <%--<td>${app.status}</td>--%>
                            <%--<td>${app.pupil}</td>--%>
                            <%--<td>${app.submittedPerson}</td>--%>
                            <%--<td class="buttons">--%>
                                <%--<a class="button positive"--%>
                                   <%--href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/details.jsp?id=${app.id}">Details</a>--%>

                                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                                      <%--method="get">--%>
                                    <%--<button class="positive" type="submit">Approve</button>--%>
                                    <%--<input type="hidden" name="status" value="approved"/>--%>
                                <%--</form>--%>
                                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                                      <%--method="get">--%>
                                    <%--<button class="negative" type="submit">Decline</button>--%>
                                    <%--<input type="hidden" name="status" value="declined"/>--%>
                                <%--</form>--%>
                            <%--</td>--%>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:if>
            </table>
                <div class="buttons">
                    <button class="positive" type="button" onclick="ivis.ui.selectItem('guardian');">OK</button>
                </div>
            </c:if>
        </div>
    </div>
</div>
<script>
    $(document).ready(
            function () {
                initialize();
            }
    );
</script>
</body>
</html>