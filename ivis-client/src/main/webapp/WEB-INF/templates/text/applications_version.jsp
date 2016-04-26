<%@ page import="com.imcode.entities.embed.Decision" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.*" %>
<%@ page import="com.imcode.imcms.addon.ivisclient.utils.Step" %>
<%@ page import="com.imcode.services.*" %>
<%@ page import="com.imcode.entities.*" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>

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
        LogEventService logEventService = factory.getService(LogEventService.class);
        EntityVersionService versionService = factory.getService(EntityVersionService.class);

        Application app;
        List<LogEvent> logs;
        List<EntityVersion> versions;
        try {
            EntityVersion version = versionService.find(Long.valueOf(request.getParameter("id")));
            app = (Application) version.getEntity();
//            app = applicationService.find(Long.valueOf(request.getParameter("id")));
            //clean empty questions
            for (ApplicationFormStep step :app.getApplicationForm().getSteps()) {
                Iterator<ApplicationFormQuestionGroup> groupIterator = step.getQuestionGroups().iterator();
                while (groupIterator.hasNext()) {
                    ApplicationFormQuestionGroup group = groupIterator.next();

                    if (group.getQuestions() == null || group.getQuestions().isEmpty()) {
                        groupIterator.remove();
                        continue;
                    }

                    boolean empty = true;

                    for (ApplicationFormQuestion question :group.getQuestions()) {
                        if (!StringUtils.isEmpty(question.getValue())) {
                            empty = false;
                            break;
                        }
                    }

                    if (empty) {
                        groupIterator.remove();
                        continue;
                    }

                }
            }

            logs = logEventService.findByEntity(app);
            versions = versionService.findByEntity(app);
            request.setAttribute("version", version);
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }

//        request.setAttribute("logs", logs);
//        request.setAttribute("versions", versions);
        request.setAttribute("app", app);
        pageContext.setAttribute("statusList", Decision.Status.values());

    }

//    if (IvisOAuth2Utils.isTokenGood(request)) {
//        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
////        ApplicationService applicationService = factory.getService(ApplicationService.class);
////        AcademicYearService academicYearService = factory.getService(AcademicYearService.class);
////        SchoolTransportService schoolTransportService = factory.getService(SchoolTransportService.class);
////        LogEventService logEventService = factory.getService(LogEventService.class);
//        EntityVersionService versionService = factory.getService(EntityVersionService.class);
//
//        Application app = null;
//
//        try {
//            EntityVersion version = versionService.find(Long.valueOf(request.getParameter("id")));
//            app = (Application) version.getEntity();
//        } catch (UserRedirectRequiredException e) {
//            IvisOAuth2Utils.setAccessToken(session, null);
//            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
//            return;
//        }
//        Map<Step, Map<String, Set<ApplicationFormQuestion>>> steps = new TreeMap<Step, Map<String, Set<ApplicationFormQuestion>>>();
//
//        for (ApplicationFormQuestion formQuestion : app.getApplicationForm().getQuestions()) {
//            Step step = new Step(formQuestion.getStepName(), formQuestion.getStepSortOrder());
//            Map<String, Set<ApplicationFormQuestion>> subSteps = steps.get(step);
//
//            if (subSteps == null) {
//                subSteps = new TreeMap<String, Set<ApplicationFormQuestion>>();
//                steps.put(step, subSteps);
//            }
//            String subStepName = formQuestion.getSubStepName() == null ? "" : formQuestion.getSubStepName();
//            Set<ApplicationFormQuestion> questions = subSteps.get(subStepName);
//
//            if (questions == null) {
//                questions = new TreeSet<ApplicationFormQuestion>();
//                subSteps.put(subStepName, questions);
//            }
//
//            questions.add(formQuestion);
//        }
//
////        List<LogEvent> logs = logEventService.findByEntity(app);
////        List<EntityVersion> versions = versionService.findByEntity(app);
////        request.setAttribute("logs", logs);
////        request.setAttribute("versions", versions);
//        request.setAttribute("steps", steps);
//        request.setAttribute("app", app);
////        pageContext.setAttribute("statusList", Decision.Status.values());
////
//    }
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
            <fmt:formatDate value="${app.createDate}" var="appCreateDate" pattern="yyy-MM-dd HH:mm:ss"/>
            <div class="value">${appCreateDate}</div>
        </div>
        <div class="group">
            <div class="title">Version</div>
            <fmt:formatDate value="${version.timestamp}" var="versionTimestamp" pattern="yyy-MM-dd HH:mm:ss"/>
            <div class="value">${versionTimestamp}</div>
        </div>
    </div>
    <div class="groups">
        <div class="group">
            <div class="title">Status</div>
            <c:choose>
                <c:when test="${appCreateDate.equals(versionTimestamp)}">
                    <div class="value">Original</div>
                </c:when>

                <c:otherwise>
                    <div class="value">${app.status.description}</div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="group">
            <div class="title">Reg.nr.</div>
            <div class="value">${app.applicationForm.id}</div>
        </div>
        <div class="group">
            <div class="buttons">
                <button class="positive">Skapa pdf</button>
            </div>
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
        <%--<div class="tab" data-tab-page-id="loggTabPage">--%>
            <%--Logg--%>
        <%--</div>--%>
        <%--<div class="tab" data-tab-page-id="versionsTabPage">--%>
            <%--Versions--%>
        <%--</div>--%>
    </div>
    <%--<div id="applicationTabPage" class="tab-page">--%>
        <%--<c:forEach items="${steps.entrySet()}" var="entry" varStatus="fileOptionStatus">--%>
            <%--<div class="step">--%>
                <%--<div class="name">${entry.key.name}</div>--%>
                <%--<div class="questions">--%>

                    <%--<c:forEach items="${entry.value.entrySet()}" var="group" varStatus="fileOptionStatus">--%>
                        <%--&lt;%&ndash;<c:choose>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:when test="${not empty subStep.key}">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="subStep">${subStep.key}</div>    &ndash;%&gt;--%>
                            <%--&lt;%&ndash;</c:when>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:otherwise>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</c:otherwise>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:choose>&ndash;%&gt;--%>
                        <%--<c:if test="${not empty group.key}">--%>
                            <%--<div class="sub-step">${group.key}</div>--%>
                        <%--</c:if>--%>
                    <%--<c:forEach items="${group.value}" var="question" varStatus="fileOptionStatus">--%>
                        <%--<div class="question">--%>
                            <%--<div class="name">${question.text}</div>--%>
                            <%--<div class="answer">${question.value}</div>--%>
                        <%--</div>--%>
                    <%--</c:forEach>--%>
                    <%--</c:forEach>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</c:forEach>--%>
    <%--</div>--%>

    <%--<div id="decisionTabPage" class="tab-page">--%>
        <%--<div class="field" id="statusSelect">--%>
            <%--<dl>--%>
                <%--<dt>Status</dt>--%>
                <%--<dd>${app.decision.status.description}</dd>--%>
                <%--<dt>Date</dt>--%>
                <%--<dd>${app.decision.date}</dd>--%>
                <%--<dt>Comment</dt>--%>
                <%--<dd>${app.decision.comment}</dd>--%>
            <%--</dl>--%>
            <%--<div class="buttons">--%>
                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                      <%--method="get">--%>
                    <%--<button class="positive" type="submit">Godk</button>--%>
                    <%--<input type="hidden" name="status" value="APPROVE"/>--%>
                <%--</form>--%>
                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                      <%--method="get">--%>
                    <%--<button class="negative" type="submit">Pågår</button>--%>
                    <%--<input type="hidden" name="status" value="DENIED"/>--%>
                <%--</form>--%>
            <%--</div>--%>

        <%--</div>--%>
    <%--</div>--%>


    <div id="applicationTabPage" class="tab-page">
        <c:forEach var="step" items="${app.applicationForm.steps}">
            <div class="step">
                <div class="name">${step.text}</div>
                <div class="questions">

                    <c:forEach items="${step.questionGroups}" var="group">
                        <div class="sub-step">
                            <div class="name">${group.text}</div>
                            <c:if test="${not fn:endsWith(group.questionType, 'TextFieldQueryInstance') and not empty group.questions}">
                                <c:set var="question" value="${group.questions.get(0)}"/>
                                <c:choose>
                                    <c:when test="${question.multiValues}">
                                        <div class="answer">
                                            <c:forEach var="value" items="${question.values}">${value}, </c:forEach>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="answer">${question.value}</div>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                        <c:if test="${fn:endsWith(group.questionType, 'TextFieldQueryInstance')}">
                            <div class="answer">
                                <c:forEach items="${group.questions}" var="question">
                                    <div class="question">
                                        <div class="name">${question.text}</div>
                                        <c:choose>
                                            <c:when test="${question.multiValues}">
                                                <div class="answer">
                                                    <c:forEach var="value"
                                                               items="${question.values}">${value}, </c:forEach>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="answer">${question.value}</div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>

    <div id="decisionTabPage" class="tab-page">
        <div class="field" id="statusSelect">
            <dl>
                <%--<dt>Status</dt>--%>
                <%--<dd>${app.decision.status.description}</dd>--%>
                <dt>Datum</dt>
                <dd><fmt:formatDate value="${app.decision.date}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
                <dt>Kommentar</dt>
                <dd>${app.decision.comment}</dd>
            </dl>
            <%--<div class="buttons">--%>
                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                      <%--method="get">--%>
                    <%--<button class="positive" type="submit">Godk</button>--%>
                    <%--<input type="hidden" name="status" value="APPROVE"/>--%>
                <%--</form>--%>
                <%--<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"--%>
                      <%--method="get">--%>
                    <%--<button class="negative" type="submit">Pågår</button>--%>
                    <%--<input type="hidden" name="status" value="DENIED"/>--%>
                <%--</form>--%>
            <%--</div>--%>

        </div>
    </div>



    <%--<div id="loggTabPage" class="tab-page">--%>
        <%--<table cellpadding="0" cellspacing="0">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th class="ordered-by">Date</th>--%>
                <%--<th>Action</th>--%>
                <%--<th>User</th>--%>
                <%--<th>Field name</th>--%>
                <%--<th>Old value</th>--%>
                <%--<th>New value</th>--%>
                <%--<th>&nbsp;</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>

            <%--<c:if test="${not empty logs}">--%>
                <%--<tbody>--%>
                <%--<c:forEach items="${logs}" var="app">--%>
                    <%--<fmt:formatDate value="${app.timestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                        <%--<tr data-application-id="${app.id}">--%>
                        <%--<td>${dateString}</td>--%>
                        <%--<td>${app.action}</td>--%>
                        <%--<td>${app.user}</td>--%>
                        <%--<td>${app.fieldName}</td>--%>
                        <%--<td>${app.previousValue}</td>--%>
                        <%--<td>${app.newValue}</td>--%>
                        <%--<td class="buttons">--%>
                            <%--&lt;%&ndash;<a class="button positive"&ndash;%&gt;--%>
                               <%--&lt;%&ndash;href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${log.id}">Visa</a>&ndash;%&gt;--%>

                                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                                <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<button class="positive" type="submit">Approve</button>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="hidden" name="status" value="APPROVE"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                                <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<button class="negative" type="submit">Decline</button>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="hidden" name="status" value="DENIED"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</c:if>--%>
        <%--</table>--%>
    <%--</div>--%>
    <%--<div id="versionsTabPage" class="tab-page">--%>
        <%--<table cellpadding="0" cellspacing="0">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th class="ordered-by">Date</th>--%>
                <%--&lt;%&ndash;<th>Action</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>User</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>Field name</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>Old value</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>New value</th>&ndash;%&gt;--%>
                <%--<th>&nbsp;</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>

            <%--<c:if test="${not empty versions}">--%>
                <%--<tbody>--%>
                <%--<c:forEach items="${versions}" var="version">--%>
                    <%--<fmt:formatDate value="${version.timestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>--%>
                        <%--<tr data-application-id="${version.id}">--%>
                        <%--<td>${dateString}</td>--%>
                        <%--&lt;%&ndash;<td>${app.action}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.user}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.fieldName}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.previousValue}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.newValue}</td>&ndash;%&gt;--%>
                        <%--<td class="buttons">--%>
                            <%--<a class="button positive"--%>
                               <%--href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/version?id=${version.id}">Visa</a>--%>

                                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                                <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<button class="positive" type="submit">Approve</button>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="hidden" name="status" value="APPROVE"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                                <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<button class="negative" type="submit">Decline</button>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<input type="hidden" name="status" value="DENIED"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</c:if>--%>
        <%--</table>--%>
    <%--</div>--%>
    <%--<a class="button positive" href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${app.id}">Edit</a>--%>
</c:if>
<script type="text/javascript">
    var onOpen = function () {
        $('#application-form').validate();
    };
</script>
<jsp:include page="ivis_footer.jsp"/>