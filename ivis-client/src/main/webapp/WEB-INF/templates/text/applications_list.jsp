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
<%@ page import="java.util.function.Predicate" %>

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
                    "PupilPersonPersonalid",
                    "PupilPersonFirstName",
                    "PupilPersonLastName",
                    "AppliedContactGuardian1PersonalId",
                    "AppliedContactGuardian1PersonFirstName",
                    "AppliedContactGuardian1PersonLastName",
                    "OtherContactPersId",
                    "OtherContactFirstName",
                    "OtherContactLastName",
                    "AppliedPupilGuardian2PersonPersonalid",
                    "AppliedPupilGuardian2PersonFirstName",
                    "AppliedPupilGuardian2PersonLastName",
                    "AppliedPupilGuardian1PersonFirstName",
                    "AppliedContactGuardian2PersonFirstName",
                    "AppliedContactGuardian2PersonLastName",
                    "AppliedContactGuardian2PersonalId"
            );
            for (Application statement : applications) {
                if (statusFilter != null && statement.getStatus() != statusFilter) {
                    continue;
                }


                if (searchText != null && StringUtils.isNoneEmpty(searchText)) {
                    Person userPerson = statement.getSubmittedUser() != null ? statement.getSubmittedUser().getPerson() : null;
                    if (IvisOAuth2Utils.personContainsString(userPerson, searchText)) {
                        applicationList.add(statement);
                        continue;
                    }
                    if (statement.getApplicationForm() == null || statement.getApplicationForm() == null) {
                        continue;
                    }
//                    Set<ApplicationFormQuestion> questions = statement.getApplicationForm().getQuestions();
                    QUESTIONS:
//                    for (ApplicationFormQuestion question : questions) {
                    for (String requredQuestionName : searchQuestionNames) {
//                        Predicate<ApplicationFormQuestion> filter = new FilterClass(requredQuestionName, searchText)
                        if (statement.findQuestion(requredQuestionName, searchText, true).isPresent()) {
                            applicationList.add(statement);
                            break QUESTIONS;
                        }
//                            if (requredQuestionName.equalsIgnoreCase(question.getXsdElementName())
//                                    && question.getValue() != null
//                                    && question.getValue().toLowerCase().contains(searchText.toLowerCase())) {
//                                applicationList.add(statement);
//                                break QUESTIONS;
//                            }
                    }
//                    }

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

        } catch
                (UserRedirectRequiredException e) {
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
            <div class="buttons inline">
                <button class="positive button" type="submit">Sök</button>
            </div>

                <%--<button class="negative" type="button" onclick="ivis.ui.clearSerchText('searchText');">Clear search</button>--%>
        </div>

        <div class="field">
            <label>Filtrera</label>
            <select name="statusFilter">
                <% String statusFilter = request.getParameter("statusFilter");
                    out.println("<option value=\"null\" " + ("null".equalsIgnoreCase(statusFilter) ? "selected\"" : "") + ">Alla</option>");
                    for (Decision.Status statementStatus : Decision.Status.values())
                        out.println("<option value=\"" + statementStatus + "\" " + (statementStatus.toString().equalsIgnoreCase(statusFilter) ? "selected" : "") + ">" + StringUtils.capitalize(statementStatus.getDescription()) + "</option>"); %>
                    <%--<option value="null" <c:if test="${not empty param.statusFilter and param.statusFilter ne 'null'}">selected</c:if>>All</option>--%>
                    <%--<c:set var="enums" value="<%=Decision.Status.values()%>"/>--%>
                    <%--<c:forEach var="enum" items="${enums}" varStatus="starus">--%>
                    <%--<option value="${starus.current}" <c:if test="${not empty param.statusFilter and param.statusFilter eq starus.current}">selected</c:if>>${starus.current}</option>--%>
                    <%--</c:forEach>--%>
            </select>

            <div class="buttons inline">
                <button class="positive button" type="submit">Filtrera</button>
            </div>
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
                    <fmt:formatDate value="${app.updateDate}" var="dateString" pattern="yyyy-MM-dd HH:mm"/>
                    <td>${dateString}</td>
                    <% Application app = (Application) pageContext.getAttribute("app");
                        String sudentName = null;
                        ApplicationForm form;
                        if (app != null && (form = app.getApplicationForm()) != null) {
//                            List<ApplicationFormQuestion> questions = app.getQuestionList();
//                            if (questions != null) {
                                String firstName = app.findQuestion("PupilPersonFirstName", true).orElse(new ApplicationFormQuestion()).getValue();
                                String lastName = app.findQuestion("PupilPersonLastName", true).orElse(new ApplicationFormQuestion()).getValue();
//                            ApplicationFormQuestion firstNameQuestion = app.findQuestion("PupilPerson.PupilPersonFirstName", true).orElse(new ApplicationFormQuestion()).getValue();
//                            if (firstNameQuestion != null) {
//                                firstName = firstNameQuestion.getValue();
//                            }
//
//                            ApplicationFormQuestion lastNameQuestion = app.findQuestion("PupilPerson.PupilPersonLastName", true);
//                            if (lastNameQuestion != null) {
//                                lastName = firstNameQuestion.getValue();
//                            }
//                                for (ApplicationFormQuestion question : questions) {
//                                    if ("PupilPerson.PupilPersonFirstName".equalsIgnoreCase(question.getXsdElementName()))
//                                        firstName = question.getValue();
//                                    if ("PupilPerson.PupilPersonLastName".equalsIgnoreCase(question.getXsdElementName()))
//                                        lastName = question.getValue();
                                    sudentName = firstName + " " + lastName;
//                                }
//                            }
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
                            <%--<input type="hidden" name="status" value="APPROVED"/>--%>
                            <%--</form>--%>
                            <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                            <%--method="get">--%>
                            <%--<button class="negative" type="submit">Decline</button>--%>
                            <%--<input type="hidden" name="status" value="DENIED"/>--%>
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
</c:if>
<jsp:include page="ivis_footer.jsp"/>