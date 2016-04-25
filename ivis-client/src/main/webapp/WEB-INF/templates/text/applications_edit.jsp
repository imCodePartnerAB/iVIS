<%@ page import="com.imcode.entities.Application,
                 com.imcode.entities.embed.Decision" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.services.AcademicYearService" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="com.imcode.services.SchoolTransportService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="com.imcode.entities.ApplicationFormQuestion" %>
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

        List<LogEvent> logs = logEventService.findByEntity(app);
        request.setAttribute("logs", logs);
//        request.setAttribute("steps", steps);
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
        <%--<div class="tab" data-tab-page-id="decisionTabPage">--%>
            <%--Beslut--%>
        <%--</div>--%>
        <%--<div class="tab" data-tab-page-id="loggTabPage">--%>
            <%--Logg--%>
        <%--</div>--%>
    </div>
    <div id="applicationTabPage" class="tab-page">
        <c:set var="applicationForm" value="${app.applicationForm}"/>
        <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/edit/${app.id}"
              method="post">
            <c:set var="index" value="${0}"/>
            <c:forEach var="step" items="${app.applicationForm.steps}">
                <div class="step">
                    <div class="name">${step.text}</div>
                    <div class="questions">
                        <c:forEach items="${step.questionGroups}" var="group">
                            <div class="sub-step">
                                <div class="name">${group.text}</div>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${fn:endsWith(group.questionType, 'DropDownQueryInstance')}">--%>
                                        <%--<div class="answer">DropDownQueryInstance</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${fn:endsWith(group.questionType, 'RadioButtonQueryInstance')}">--%>
                                        <%--<div class="answer">RadioButtonQueryInstance</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${fn:endsWith(group.questionType, 'CheckboxQueryInstance')}">--%>
                                        <%--<div class="answer">CheckboxQueryInstance</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:when test="${fn:endsWith(group.questionType, '')}">--%>
                                    <%--<div class="answer"></div>--%>
                                    <%--</c:when>--%>

                                <%--</c:choose>--%>
                                    <%--<c:if test="${not fn:endsWith(group.questionType, 'TextFieldQueryInstance') and not empty group.questions}">--%>
                                    <%--<c:set var="question" value="${group.questions.get(0)}"/>--%>
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${question.multiValues}">--%>
                                    <%--<div class="answer">--%>
                                    <%--<c:forEach var="value" items="${question.values}">${value}, </c:forEach>--%>
                                    <%--</div>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                    <%--<div class="answer">${question.value}</div>--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                    <%--</c:if>--%>
                            </div>
                            <c:choose>
                                <%--<c:when test="${fn:endsWith(group.questionType, '2.TextFieldQueryInstance')}">--%>
                                    <%--&lt;%&ndash;<div class="answer">LabelFieldQueryInstance</div>&ndash;%&gt;--%>
                                    <%--<c:forEach items="${group.questions}" var="question">--%>
                                        <%--<div class="question">--%>
                                            <%--<div class="name">${question.text}</div>--%>
                                            <%--<div class="answer">${question.value}</div>--%>
                                        <%--</div>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:when>--%>
                                <%--<c:when test="${fn:endsWith(group.questionType, 'y.TextFieldQueryInstance')}">--%>
                                    <%--<c:forEach items="${group.questions}" var="question">--%>
                                        <%--<div class="question">--%>
                                            <%--<div class="name">${question.text}</div>--%>
                                            <%--<input type="hidden" name="questions[${index}].id" value="${question.id}"/>--%>
                                                <%--<input name="questions[${index}].value" value="${question.value}"/>--%>
                                                <%--<c:set var="index" value="${index + 1}"/>--%>
                                        <%--</div>--%>
                                    <%--</c:forEach>--%>
                                <%--</c:when>--%>
                                <c:when test="${fn:endsWith(group.questionType, 'TextFieldQueryInstance')}">
                                    <c:forEach items="${group.questions}" var="question">
                                        <div class="question">
                                            <div class="name">${question.text}</div>
                                            <input type="hidden" name="questions[${index}].id" value="${question.id}"/>
                                                <input name="questions[${index}].value" value="${question.value}"/>
                                                <c:set var="index" value="${index + 1}"/>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${fn:endsWith(group.questionType, 'TextAreaQueryInstance')}">
                                    <c:set var="question" value="${group.questions[0]}"/>
                                    <div class="question">
                                        <div class="name"> </div>
                                        <input type="hidden" name="questions[${index}].id" value="${question.id}"/>
                                        <textarea name="questions[${index}].value">${question.value}</textarea>
                                        <c:set var="index" value="${index + 1}"/>
                                    </div>
                                </c:when>
                                <c:when test="${fn:endsWith(group.questionType, 'DropDownQueryInstance')}">
                                    <c:set var="question" value="${group.questions[0]}"/>
                                    <div class="question">
                                        <div class="name"> </div>
                                        <input type="hidden" name="questions[${index}].id" value="${question.id}"/>
                                        <select name="questions[${index}].value">
                                            <option/>
                                            <c:forEach var="variant" items="${question.variants}">
                                                <option value="${variant}" <c:if test="${variant.equalsIgnoreCase(question.value)}">selected="selected" </c:if>>${variant}</option>
                                            </c:forEach>
                                        </select>
                                        <c:set var="index" value="${index + 1}"/>
                                    </div>
                                </c:when>
                                <c:when test="${fn:endsWith(group.questionType, 'RadioButtonQueryInstance')}">
                                    <c:set var="question" value="${group.questions[0]}"/>
                                    <div class="question">
                                        <%--<div class="name"> </div>--%>
                                        <input type="hidden" name="questions[${index}].id" value="${question.id}"/>
                                            <c:forEach var="variant" items="${question.variants}">
                                                <div class="name"> </div>
                                                <input class="radio-checkbox-input " type="radio" name="questions[${index}].value" value="${variant}" <c:if test="${variant.equalsIgnoreCase(question.value)}">checked="checked" </c:if>> ${variant}<br>
                                                <div class="clear"></div>
                                            </c:forEach>
                                        <c:set var="index" value="${index + 1}"/>
                                    </div>
                                </c:when>
                                <c:when test="${fn:endsWith(group.questionType, 'CheckboxQueryInstance')}">
                                    <c:set var="question" value="${group.questions[0]}"/>
                                    <div class="question">
                                        <input type="hidden" name="questions[${index}].id" value="${question.id}"/>
                                        <c:forEach var="variant" items="${question.variants}">
                                            <c:set var="checked" value="false"/>
                                            <c:forEach var="value" items="${question.values}">
                                                <c:if test="${not checked and value eq variant}">
                                                <c:set var="checked" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <div class="name"> </div>
                                            <input class="radio-checkbox-input " type="checkbox" name="questions[${index}].values" value="${variant}" <c:if test="${checked}">checked="checked" </c:if>> ${variant}<br>
                                            <div class="clear"></div>
                                        </c:forEach>
                                        <c:set var="index" value="${index + 1}"/>
                                    </div>
                                </c:when>
                            </c:choose>
                            <%--<div class="answer">--%>
                            <%--<c:forEach items="${group.questions}" var="question">--%>
                            <%--<div class="question">--%>
                            <%--<div class="name">${question.text}</div>--%>
                            <%--<c:choose>--%>
                            <%--<c:when test="${question.multiValues}">--%>
                            <%--<div class="answer">--%>
                            <%--<c:forEach var="value"--%>
                            <%--items="${question.values}">${value}, </c:forEach>--%>
                            <%--</div>--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                            <%--<div class="answer">${question.value}</div>--%>
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                            <%--</div>--%>
                            <%--</c:forEach>--%>
                            <%--</div>--%>
                            <%--</c:if>--%>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
                <%--<c:forEach items="${steps.entrySet()}" var="entry">--%>
                <%--<div class="step">--%>
                <%--<div class="name">${entry.key.name}</div>--%>
                <%--<div class="questions">--%>
                <%--<c:forEach items="${entry.value.entrySet()}" var="group">--%>
                <%--<c:if test="${not empty group.key}">--%>
                <%--<div class="sub-step">${group.key}</div>--%>
                <%--</c:if>--%>
                <%--<c:forEach items="${group.value}" var="question" varStatus="varStatus">--%>
                <%--<div class="question">--%>
                <%--<div class="name">${question.text}</div>--%>
                <%--&lt;%&ndash;<form:input path="questions[${varStatus.index}].xsdElementName"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="answer">${question.value}</div>&ndash;%&gt;--%>
                <%--<input type="hidden" name="questions[${index}].xsdElementName" value="${question.xsdElementName}"/>--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions.xsdElementName" value="${question.xsdElementName}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions[${varStatus.index}].sortOrder" value="${question.sortOrder}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions[${varStatus.index}].subStepName" value="${question.subStepName}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions[${varStatus.index}].stepName" value="${question.stepName}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions[${varStatus.index}].stepSortOrder" value="${question.stepSortOrder}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="hidden" name="questions[${varStatus.index}].text" value="${question.text}"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input name="questions[${varStatus.index}].value" value="${question.value}"/>&ndash;%&gt;--%>

                <%--<input type="hidden" name="questions[${index}].sortOrder" value="${question.sortOrder}"/>--%>
                <%--<input type="hidden" name="questions[${index}].subStepName" value="${question.subStepName}"/>--%>
                <%--<input type="hidden" name="questions[${index}].stepName" value="${question.stepName}"/>--%>
                <%--<input type="hidden" name="questions[${index}].stepSortOrder" value="${question.stepSortOrder}"/>--%>
                <%--<input type="hidden" name="questions[${index}].text" value="${question.text}"/>--%>
                <%--<input name="questions[${index}].value" value="${question.value}"/>--%>
                <%--<c:set var="index" value="${index + 1}"/>--%>
                <%--</div>--%>
                <%--</c:forEach>--%>
                <%--</c:forEach>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</c:forEach>--%>
            <div class="buttons">
                <button class="positive  leveling" type="submit">Spara</button>
                <button class="negative  leveling spacer" type="reset" onclick="location.href = location.href.replace('edit', 'show')">
                    Avbryt
                </button>
            </div>
        </form>
        <div class="clear"></div>
    </div>

    <%--<div id="decisionTabPage" class="tab-page">--%>
        <%--<div class="field" id="statusSelect">--%>
            <%--<dl>--%>
                <%--<dt>Status</dt>--%>
                <%--<dd>${app.decision.status.description}</dd>--%>
                <%--<dt>Date</dt>--%>
                <%--<dd><fmt:formatDate value="${app.decision.date}" pattern="yyyy-MM-dd HH:mm:ss"/></dd>--%>
                <%--<dt>Comment</dt>--%>
                <%--<dd>${app.decision.comment}</dd>--%>
            <%--</dl>--%>
            <%--&lt;%&ndash;<div class="buttons">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                      <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<button class="positive leveling" type="submit">Godk</button>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<input type="hidden" name="status" value="APPROVE"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
                      <%--&lt;%&ndash;method="get">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<button class="negative leveling spacer" type="submit">Pågår</button>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<input type="hidden" name="status" value="DENIED"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<div class="clear"></div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--</div>--%>
    <%--</div>--%>
    <%--<div id="loggTabPage" class="tab-page">--%>
        <%--<table cellpadding="0" cellspacing="0">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th class="ordered-by">Datum</th>--%>
                <%--<th>åtgärd</th>--%>
                <%--<th>användare</th>--%>
                <%--&lt;%&ndash;<th>Field name</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>Old value</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>New value</th>&ndash;%&gt;--%>
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
                        <%--&lt;%&ndash;<td>${app.fieldName}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.previousValue}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>${app.newValue}</td>&ndash;%&gt;--%>
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
</c:if>
<script type="text/javascript">
    var onOpen = function () {
        $('#application-form').validate();
    };
</script>
<jsp:include page="ivis_footer.jsp"/>