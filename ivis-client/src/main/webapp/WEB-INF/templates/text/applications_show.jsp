<%@ page import="com.imcode.entities.Application,
                 com.imcode.entities.embed.Decision" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.services.AcademicYearService" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="com.imcode.services.SchoolTransportService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="com.imcode.entities.embed.ApplicationFormQuestion" %>
<%@ page import="java.util.*" %>
<%@ page import="com.imcode.imcms.addon.ivisclient.utils.Step" %>
<%@ page import="com.imcode.services.LogEventService" %>
<%@ page import="com.imcode.entities.LogEvent" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ihtml" tagdir="/WEB-INF/tags/html" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>
<%
    if (IvisOAuth2Utils.isTokenGood(request)) {
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        ApplicationService applicationService = factory.getService(ApplicationService.class);
        AcademicYearService academicYearService = factory.getService(AcademicYearService.class);
        SchoolTransportService schoolTransportService = factory.getService(SchoolTransportService.class);
        LogEventService logEventService = factory.getService(LogEventService.class);

        Application app = null;

        try {
            app = applicationService.find(Long.valueOf(request.getParameter("id")));
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }
        Map<Step, Map<String, Set<ApplicationFormQuestion>>> steps = new TreeMap<Step, Map<String, Set<ApplicationFormQuestion>>>();

        for (ApplicationFormQuestion formQuestion : app.getApplicationForm().getQuestions()) {
            Step step = new Step(formQuestion.getStepName(), formQuestion.getStepSortOrder());
            Map<String, Set<ApplicationFormQuestion>> subSteps = steps.get(step);

            if (subSteps == null) {
                subSteps = new TreeMap<String, Set<ApplicationFormQuestion>>();
                steps.put(step, subSteps);
            }
            String subStepName = formQuestion.getSubStepName() == null ? "" : formQuestion.getSubStepName();
            Set<ApplicationFormQuestion> questions = subSteps.get(subStepName);

            if (questions == null) {
                questions = new TreeSet<ApplicationFormQuestion>();
                subSteps.put(subStepName, questions);
            }

            questions.add(formQuestion);
        }

        List<LogEvent> logs = logEventService.findByEntity(app);
        request.setAttribute("logs", logs);
        request.setAttribute("steps", steps);
        request.setAttribute("app", app);
        pageContext.setAttribute("statusList", Decision.Status.values());

    }
%>
<c:if test="${not empty app}">
    <h1>Ansökan om skolskjuts</h1>
    <div class="groups">
        <div class="group">
            <div class="title">ID</div>
            <div class="value">${app.id}</div>
        </div>
        <div class="group">
            <div class="title">Skapad</div>
            <div class="value"><fmt:formatDate value="${app.createDate}" pattern="yyy-MM-dd HH:mm:ss"/></div>
        </div>
        <div class="group">
            <div class="title">Senast ändrad</div>
            <div class="value"><fmt:formatDate value="${app.updateDate}" pattern="yyy-MM-dd HH:mm:ss"/></div>
        </div>
    </div>
    <div class="groups">
        <div class="group">
            <div class="title">Status</div>
            <div class="value">${app.status.description}</div>
        </div>
        <div class="group">
            <div class="title">Reg.nr.</div>
            <div class="value">${app.applicationForm.id}</div>
        </div>
        <div class="group" style="display: none">
            <div class="title">Handläggs av</div>
            <div class="value">${app.handledUser}</div>
        </div>
    </div>
    <%--<h2>Skapas</h2><fmt:formatDate value="${app.createDate}" pattern="yyy-MM-dd HH:mm:ss"/>--%>
    <%--<h2>Senast ändrad</h2><fmt:formatDate value="${app.updateDate}" pattern="yyy-MM-dd HH:mm:ss"/>--%>
    <%--<h2>Status</h2>${app.status.description}--%>
    <%--<h2>Handläggs av</h2>${app.handledUser}--%>

    <div class="tabs">
        <div class="tab" data-tab-page-id="applicationTabPage">
            Ansökan
        </div>
        <div class="tab" data-tab-page-id="decisionTabPage">
            Beslut
        </div>
        <div class="tab" data-tab-page-id="loggTabPage">
            Logg
        </div>
    </div>
    <div id="applicationTabPage" class="tab-page">
        <c:forEach items="${steps.entrySet()}" var="entry" varStatus="fileOptionStatus">
            <div class="step">
                <div class="name">${entry.key.name}</div>
                <div class="questions">

                    <c:forEach items="${entry.value.entrySet()}" var="subStep" varStatus="fileOptionStatus">
                        <%--<c:choose>--%>
                            <%--<c:when test="${not empty subStep.key}">--%>
                                <%--<div class="subStep">${subStep.key}</div>    --%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%----%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                        <c:if test="${not empty subStep.key}">
                            <div class="sub-step">${subStep.key}</div>
                        </c:if>
                    <c:forEach items="${subStep.value}" var="question" varStatus="fileOptionStatus">
                        <div class="question">
                            <div class="name">${question.text}</div>
                            <div class="answer">${question.value}</div>
                        </div>
                    </c:forEach>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

    <div id="decisionTabPage" class="tab-page">
        <div class="field" id="statusSelect">
            <dl>
                <dt>Status</dt>
                <dd>${app.decision.status.description}</dd>
                <dt>Date</dt>
                <dd>${app.decision.date}</dd>
                <dt>Comment</dt>
                <dd>${app.decision.comment}</dd>
            </dl>
            <div class="buttons">
                <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                      method="get">
                    <button class="positive" type="submit">Godk</button>
                    <input type="hidden" name="status" value="APPROVE"/>
                </form>
                <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                      method="get">
                    <button class="negative" type="submit">Pågår</button>
                    <input type="hidden" name="status" value="DENI"/>
                </form>
            </div>

        </div>
    </div>
    <div id="loggTabPage" class="tab-page">
        <table cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th class="ordered-by">Date</th>
                <th>Action</th>
                <th>User</th>
                <th>Field name</th>
                <th>Old value</th>
                <th>New value</th>
                <th>&nbsp;</th>
            </tr>
            </thead>

            <c:if test="${not empty logs}">
                <tbody>
                <c:forEach items="${logs}" var="app">
                    <fmt:formatDate value="${app.timestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>
                        <tr data-application-id="${app.id}">
                        <td>${dateString}</td>
                        <td>${app.action}</td>
                        <td>${app.user}</td>
                        <td>${app.fieldName}</td>
                        <td>${app.previousValue}</td>
                        <td>${app.newValue}</td>
                        <td class="buttons">
                            <%--<a class="button positive"--%>
                               <%--href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${log.id}">Visa</a>--%>

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
    </div>
    <a class="button positive" href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${app.id}">Edit</a>
</c:if>
<script type="text/javascript">
    var onOpen = function () {
        $('#application-form').validate();
    };
</script>
<jsp:include page="ivis_footer.jsp"/>