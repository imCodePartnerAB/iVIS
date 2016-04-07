<%@ page import="com.imcode.entities.*" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.entities.embed.Decision" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="com.imcode.services.EntityVersionService" %>
<%@ page import="com.imcode.services.LogEventService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>

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
            app = applicationService.find(Long.valueOf(request.getParameter("id")));
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
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }

        request.setAttribute("logs", logs);
        request.setAttribute("versions", versions);
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
        <div class="tab" data-tab-page-id="versionsTabPage">
            Versions
        </div>
    </div>
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
                    <button class="positive leveling" type="submit">Godk</button>
                    <input type="hidden" name="status" value="APPROVE"/>
                </form>
                <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                      method="get">
                    <button class="negative leveling spacer" type="submit">Pågår</button>
                    <input type="hidden" name="status" value="DENI"/>
                </form>
                <div class="clear"></div>
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
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
        </table>
    </div>
    <div id="versionsTabPage" class="tab-page">
        <table cellpadding="0" cellspacing="0">
            <thead>
            <tr>
                <th class="ordered-by">Date</th>
                <th>&nbsp;</th>
            </tr>
            </thead>

            <c:if test="${not empty versions}">
                <tbody>
                <c:forEach items="${versions}" var="version">
                    <fmt:formatDate value="${version.timestamp}" var="dateString" pattern="yyyy-MM-dd HH:mm:ss"/>
                    <tr data-application-id="${version.id}">
                        <td>${dateString}</td>
                        <td class="buttons">
                            <a class="button positive"
                               href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/version?id=${version.id}">Visa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
        </table>
    </div>
    <div class="buttons">
        <a class="button positive"
           href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/edit?id=${app.id}">Edit</a>
    </div>

</c:if>
<script type="text/javascript">
    var onOpen = function () {
        $('#application-form').validate();
    };
</script>
<jsp:include page="ivis_footer.jsp"/>