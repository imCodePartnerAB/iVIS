<%@ page import="com.imcode.entities.AcademicYear,
                 com.imcode.entities.Application" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.entities.SchoolTransport" %>
<%@ page import="com.imcode.entities.embed.SchoolTransportSchema" %>
<%@ page import="com.imcode.services.AcademicYearService" %>
<%@ page import="com.imcode.services.ApplicationService" %>
<%@ page import="com.imcode.services.SchoolTransportService" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="java.time.DayOfWeek" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.LinkedList" %>
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
        AcademicYearService academicYearService = factory.getService(AcademicYearService.class);
        SchoolTransportService schoolTransportService = factory.getService(SchoolTransportService.class);

        Application app = null;

        try {
            app = applicationService.find(Long.valueOf(request.getParameter("id")));
        } catch (UserRedirectRequiredException e) {
            IvisOAuth2Utils.setAccessToken(session, null);
            response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
            return;
        }

        List<AcademicYear> academicYearList = new ArrayList();

        try {
            academicYearList = academicYearService.findAll();
        } catch (Exception ignore) {
        }

        List<SchoolTransport> schoolTransportList = new ArrayList();

        try {
            schoolTransportList = schoolTransportService.findAll();
        } catch (Exception ignore) {
        }

        List<String> reasoneList = new ArrayList();

        try {
            reasoneList.add("Ressont #1");
            reasoneList.add("Ressont #2");
            reasoneList.add("Ressont #3");
            reasoneList.add("Ressont #4");
        } catch (Exception ignore) {
        }

        List<SchoolTransportSchema> schoolTransportSchema = new LinkedList<SchoolTransportSchema>();

        try {
//            schoolTransportSchema = app.getSchoolTransportSchema();
            List<SchoolTransportSchema> ApplicationschoolTransportSchema = app.getSchoolTransportSchema();

            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                SchoolTransportSchema schema = new SchoolTransportSchema(dayOfWeek);

                for (SchoolTransportSchema centerSchema : ApplicationschoolTransportSchema) {
                    if (dayOfWeek.equals(centerSchema.getDayOfWeek())) {
                        schema = centerSchema;
                        break;
                    }
                }

                schoolTransportSchema.add(schema);
            }
        } catch (Exception ignore) {
        }

        request.setAttribute("schoolTransportSchema", schoolTransportSchema);

        request.setAttribute("app", app);
        request.setAttribute("academicYearList", academicYearList);
        request.setAttribute("schoolTransportList", schoolTransportList);
        request.setAttribute("reasoneList", reasoneList);
//        pageContext.setAttribute("statusList", StatementStatus.values());

    }
%>
<c:if test="${not empty app}">
    <%--<h1>Application</h1>--%>
    <h1>School transport - issue ${app.id}</h1>

    <h2>Subbmited</h2><fmt:formatDate value="${app.submitDate}" pattern="yyy-MM-dd HH:mm:ss"/>
    <h2>Last change</h2><fmt:formatDate value="${app.changedDate}" pattern="yyy-MM-dd HH:mm:ss"/>
    <h2>Status</h2>${app.status}
    <h2>Handled by</h2>${app.handledPerson}

    <div class="tabs">
        <div class="tab" data-tab-page-id="applicationTabPage">
            Application
        </div>
        <div class="tab" data-tab-page-id="decisionTabPage">
            Decision
        </div>
        <div class="tab" data-tab-page-id="loggTabPage">
            Logg
        </div>
    </div>
    <%--<form:form modelAttribute="pupil" action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/pupils" method="post">--%>
    <form:form modelAttribute="app" action="${clientAddress}/api/content/ivis/applications" method="post"
               id="application-form">
        <div id="applicationTabPage" class="tab-page">
            <input type="hidden" name="app" value="${app.id}">
                <ihtml:personalizedShortInfo value="${app.pupil}" path="pupil" editUrl="${clientAddress}/pupils/edit"
                                             label="Pupil"/>
            <ihtml:personalizedShortInfo value="${app.guardian}" path="guardian" editUrl="" label="Guardian"/>
            <ihtml:addressShortInfo value="${app.address}" label="Address"/>
            <div>
                <h2>Requested school transport</h2>
                <ihtml:formSelect items="${academicYearList}" path="academicYear" label="School transport for period"/>
                <ihtml:formSelect items="${schoolTransportList}" path="schoolTransport" label="Means of conveyance"/>
                <div class="field" id="${path}Select">
                    <form:label path="reasone">Reason for transport</form:label>
                    <form:select path="reasone">
                        <form:option value="">-----</form:option>
                        <form:options items="${reasoneList}"/>
                    </form:select>
                    <form:errors path="reasone" cssClass="error-description"/>
                </div>


            </div>
            <div>
                <h2>School transport is required to school</h2>
                <table cellpadding="0" cellspacing="0">
                    <thead>
                    <tr>
                        <c:forEach items="${schoolTransportSchema}" var="schema">
                            <td>
                                    ${schema.dayOfWeek}
                            </td>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schoolTransportSchema}" var="schema" varStatus="fileOptionStatus">
                        <td>
                            <input type="hidden" name="schoolTransportSchema[${fileOptionStatus.index}].dayOfWeek"
                                   value="${schema.dayOfWeek}">
                            <input type="checkbox"
                                   name="schoolTransportSchema[${fileOptionStatus.index}].to" ${schema.isTo() ? "checked": ""}>

                        </td>
                    </c:forEach>
                    </tbody>
                </table>
                <h2>School transport is required from school</h2>
                <table cellpadding="0" cellspacing="0">
                    <thead>
                    <tr>
                        <c:forEach items="${schoolTransportSchema}" var="schema">
                            <td>${schema.dayOfWeek}</td>
                        </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${schoolTransportSchema}" var="schema" varStatus="fileOptionStatus">
                        <td>
                            <input type="hidden" name="schoolTransportSchema[${fileOptionStatus.index}].dayOfWeek"
                                   value="${schema.dayOfWeek}">
                            <input type="checkbox"
                                   name="schoolTransportSchema[${fileOptionStatus.index}].from" ${schema.isFrom() ? "checked": ""}>

                        </td>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <h2>School transport is required from school</h2>
                <div class="checkbox">
                    <form:checkbox path="accompanyingAssistant" />
                    <form:label path="accompanyingAssistant">Accompanying assistant</form:label>

                    <form:checkbox path="byMobilPhone"/>
                    <form:label path="byMobilPhone">Permobil</form:label>

                    <form:checkbox path="shorty"/>
                    <form:label path="shorty">Shorter than 135 centimeters</form:label>

                    <form:checkbox path="wheelchair"/>
                    <form:label path="wheelchair">Wheelchair</form:label>
                </div>
            </div>
        </div>

        <div id="decisionTabPage" class="tab-page">
            <div class="field" id="statusSelect">
                <form:label path="status">Status</form:label>
                <form:select path="status">
                    <form:option value="">-----</form:option>
                    <form:options items="${statusList}"/>
                </form:select>
                <form:errors path="reasone" cssClass="error-description"/>
            </div>
        </div>
        <div id="loggTabPage" class="tab-page">
        </div>
        <div class="buttons">
            <button class="positive" type="submit">Save</button>
            <a class="button neutral" type="/pupils">Cancel</a>
        </div>
    </form:form>
</c:if>
<script type="text/javascript">
    <%--var communicationTypeEnum = [<%--%>
    <%--Enum[] values = CommunicationTypeEnum.values();--%>

    <%--for(int i = 0; i < values.length; i++) {--%>
    <%--Enum typeEnum = values[i];--%>
    <%--out.print("{name:\"");--%>
    <%--out.print(typeEnum.name());--%>
    <%--out.print("\", description:\"");--%>
    <%--out.print(typeEnum.toString());--%>
    <%--out.print("\"}, ");--%>
    <%--}--%>
    <%--%>];--%>

    <%--var addressTypeEnum = [<%--%>
    <%--values = AddressTypeEnum.values();--%>

    <%--for(int i = 0; i < values.length; i++) {--%>
    <%--Enum typeEnum = values[i];--%>
    <%--out.print("{name:\"");--%>
    <%--out.print(typeEnum.name());--%>
    <%--out.print("\", description:\"");--%>
    <%--out.print(typeEnum.toString());--%>
    <%--out.print("\"}, ");--%>
    <%--}--%>
    <%--%>];--%>
//    $(document).ready(function () {
////        alert("asdfasdf");
//        $('#application-form').validate();
//    });
    var onOpen = function () {
        $('#application-form').validate();
    };
</script>
<%--<c:if test="${not empty statement}">--%>
<%--<table cellpadding="0" cellspacing="0">--%>
<%--<tbody>--%>
<%--<tr>--%>
<%--<td>Id</td>--%>
<%--<td>${statement.id}</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Submit date</td>--%>
<%--<td>${statement.submitDate}</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Change date</td>--%>
<%--<td>${statement.changedDate}</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Status</td>--%>
<%--<td>${statement.status}</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Submitted person</td>--%>
<%--<td>${statement.submittedPerson}</td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Pupil</td>--%>
<%--<td>${statement.pupil}</td>--%>
<%--</tr>--%>
<%--</tbody>--%>
<%--</table>--%>
<%--<div class="buttons">--%>
<%--<a class="button neutral" href="<%=Imcms.getServerProperties().getProperty("StatementsAddress")%>">Back</a>--%>
<%--</div>--%>
<%--</c:if>--%>
<jsp:include page="ivis_footer.jsp"/>