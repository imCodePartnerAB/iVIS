<%@ page import="com.imcode.entities.Person,
                 com.imcode.entities.Application" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="com.imcode.entities.embed.Decision" %>
<%@ page import="com.imcode.entities.ApplicationForm" %>
<%@ page import="java.util.*" %>
<%@ page import="com.imcode.entities.ApplicationFormQuestion" %>

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
        } catch (Exception ignore) {
        }

        List<Application> applicationList = null;
        try {
            List<Application> applications = service.findAll();
            applicationList = new LinkedList<Application>();
            List<String> searchQuestionNames = Arrays.asList(
                    "PupilPerson.PupilPersonPersonalid",
                    "PupilPerson.PupilPersonFirstName",
                    "PupilPerson.PupilPersonLastName",
                    "KontaktuppgifterVardnadshavare1.AppliedContactGuardian1PersonalId",
                    "KontaktuppgifterVardnadshavare1.AppliedContactGuardian1PersonFirstName",
                    "KontaktuppgifterVardnadshavare1.AppliedContactGuardian1PersonLastName",
                    "OtherContactPers.OtherContactPersId",
                    "OtherContactPers.OtherContactFirstName",
                    "OtherContactPers.OtherContactLastName",
                    "AppliedPupilPersonAddress2.AppliedPupilGuardian2PersonPersonalid",
                    "AppliedPupilPersonAddress2.AppliedPupilGuardian2PersonFirstName",
                    "AppliedPupilPersonAddress2.AppliedPupilGuardian2PersonLastName",
                    "AppliedPupilPersonAddress.AppliedPupilGuardian1PersonFirstName",
                    "KontaktuppgifterVardnadshavare2.AppliedContactGuardian2PersonFirstName",
                    "KontaktuppgifterVardnadshavare2.AppliedContactGuardian2PersonLastName",
                    "KontaktuppgifterVardnadshavare2.AppliedContactGuardian2PersonalId"
                    );

            for (Application statement : applications) {
                if (statusFilter != null && statement.getStatus() != statusFilter) {
                    continue;
                }


                if (searchText != null && StringUtils.isNoneEmpty(searchText)) {
                    Person userPerson = statement.getSubmittedUser() != null ? statement.getSubmittedUser().getPerson():null;
                    if (IvisOAuth2Utils.personContainsString(userPerson, searchText)) {
                        applicationList.add(statement);
                        continue;
                    }
                    if (statement.getApplicationForm() == null || statement.getApplicationForm().getQuestions() == null) {
                        continue;
                    }
                    Set<ApplicationFormQuestion> questions = statement.getApplicationForm().getQuestions();
                    QUESTIONS:
                    for (ApplicationFormQuestion question : questions) {
                        for (String requredQuestionName : searchQuestionNames) {
                            if (requredQuestionName.equalsIgnoreCase(question.getXsdElementName())
                                    && question.getValue() != null
                                    && question.getValue().toLowerCase().contains(searchText.toLowerCase())) {
                                applicationList.add(statement);
                                break QUESTIONS;
                            }
                        }
                    }
                } else {
                    applicationList.add(statement);
                }

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
                    out.println("<option value=\"null\" " + ("null".equalsIgnoreCase(statusFilter) ? "selected\"" : "") + ">Alla</option>");

                    for (Decision.Status statementStatus : Decision.Status.values()) {
                        out.println("<option value=\"" + statementStatus + "\" " + (statementStatus.toString().equalsIgnoreCase(statusFilter) ? "selected" : "") + ">" + StringUtils.capitalize(statementStatus.getDescription()) + "</option>");
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
            <th>Ändrad</th>
            <th>Elev</th>
            <th>Status</th>
                <%--<th>Reg</th>--%>
                <%--<th>Av</th>--%>
            <th>&nbsp;</th>
        </tr>
        </thead>

        <c:if test="${not empty statements}">
            <tbody>
            <c:forEach items="${statements}" var="app">
                <tr data-application-id="${app.id}"
                    onclick="location.href='<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/show?id=${app.id}';">
                    <td>${app.id}</td>
                    <fmt:formatDate value="${app.createDate}" var="dateString" pattern="yyyy-MM-dd HH:mm"/>
                    <td>${dateString}</td>
                    <fmt:formatDate value="${app.createDate}" var="dateString" pattern="yyyy-MM-dd HH:mm"/>
                    <td>${dateString}</td>
                    <%
                        Application app = (Application) pageContext.getAttribute("app");
                        String sudentName = null;
                        ApplicationForm form;
                        if (app != null && (form = app.getApplicationForm()) != null) {
                            Set<ApplicationFormQuestion> questions = form.getQuestions();
                            if (questions != null) {
                                String firstName = "";
                                String lastName = "";
                                for (ApplicationFormQuestion question : questions) {
                                    if ("PupilPerson.PupilPersonFirstName".equalsIgnoreCase(question.getXsdElementName())) {
                                        firstName = question.getValue();
                                    }
                                    if ("PupilPerson.PupilPersonLastName".equalsIgnoreCase(question.getXsdElementName())) {
                                        lastName = question.getValue();
                                    }
                                    sudentName = firstName + " " + lastName;
                                }
                            }
                        }

                        pageContext.setAttribute("studentName", sudentName);
                    %>
                    <td>${studentName}</td>
                    <td>${app.status.description}</td>
                        <%--<td>${log.registrationNumber}</td>--%>
                        <%--<td>${app.submittedUser}</td>--%>
                    <td class="buttons">
                        <a class="button positive"
                           href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/show?id=${app.id}">Visa</a>

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