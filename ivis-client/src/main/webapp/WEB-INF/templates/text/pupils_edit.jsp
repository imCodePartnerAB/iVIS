<%@ page import="com.imcode.entities.Pupil,
                 com.imcode.services.PupilService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="com.imcode.entities.enums.AddressTypeEnum" %>
<%@ page import="com.imcode.entities.enums.CommunicationTypeEnum" %>
<%@ page import="java.util.Arrays" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<imcms:variables/>
<jsp:include page="ivis_header.jsp"/>
<%
    if (IvisOAuth2Utils.isTokenGood(request)) {
        IvisServiceFactory factory = IvisOAuth2Utils.getServiceFactory(request);
        PupilService service = factory.getService(PupilService.class);
        Pupil pupil = new Pupil();
        Long id = null;

        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (id != null) {
            try {
                pupil = service.find(Long.valueOf(id));
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(session, null);
                response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
                return;
            }
        }
        request.setAttribute("pupil", pupil);
    }

    request.setAttribute("communicationTypeEnum", Arrays.asList(CommunicationTypeEnum.values()));
    request.setAttribute("addressTypeEnum", AddressTypeEnum.values());
%>
<c:if test="${not empty pupil}">
    <h1>Pupil - ${pupil.person.firstName} ${pupil.person.lastName}</h1>
    <%--<table cellpadding="0" cellspacing="0">--%>
    <%--<tbody>--%>
    <%--<tr>--%>
    <%--<td>Id</td>--%>
    <%--<td>${pupil.id}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Submit date</td>--%>
    <%--<td>${pupil.submitDate}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Change date</td>--%>
    <%--<td>${pupil.changedDate}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Status</td>--%>
    <%--<td>${pupil.status}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Submitted person</td>--%>
    <%--<td>${pupil.submittedPerson}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
    <%--<td>Pupil</td>--%>
    <%--<td>${pupil.pupil}</td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
    <%--</table>--%>
    <%--<div class="buttons">--%>
    <%--<a class="button neutral" href="/pupils">&lt;&lt;Back to list</a>--%>
    <%--</div>--%>

    <div class="tabs">
        <div class="tab" data-tab-page-id="basicDataTabPage">
            Basic data
        </div>
        <div class="tab" data-tab-page-id="guardiansTabPage">
            Guardians
        </div>
        <div class="tab" data-tab-page-id="schoolAndClassTabPage">
            School & class
        </div>
        <div class="tab" data-tab-page-id="schoolTransportTabPage">
            School transport
        </div>
        <div class="tab" data-tab-page-id="loggTabPage">
            Logg
        </div>
    </div>
    <%--<form:form modelAttribute="pupil" action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/pupils" method="post">--%>
    <form:form modelAttribute="pupil" action="http://localhost:8080/client/api/content/ivis/pupils" method="post">
        <div id="basicDataTabPage" class="tab-page">
            <form:hidden path="id" cssErrorClass="error"/>
            <form:hidden path="person.id" cssErrorClass="error"/>
                <%--<div class="field">--%>
                <%--<form:label path=""></form:label>--%>
                <%--<form:input path="" cssErrorClass="error"/>--%>
                <%--<form:errors path="" cssClass="error-description"/>--%>
                <%--</div>--%>
            <div class="field">
                <form:label path="person.personalId">Personal ID</form:label>
                <form:input path="person.personalId" cssErrorClass="error"/>
                <form:errors path="person.personalId" cssClass="error-description"/>
            </div>
            <div class="field">
                <form:label path="person.lastName">Last name</form:label>
                <form:input path="person.lastName" cssErrorClass="error"/>
                <form:errors path="person.lastName" cssClass="error-description"/>
            </div>
            <div class="field">
                <form:label path="person.firstName">
                    First name
                </form:label>
                <form:input path="person.firstName" cssErrorClass="error"/>
                <form:errors path="person.firstName" cssClass="error-description"/>
            </div>

            <h2>Addresses:</h2>
            <div id="addresses">
                <c:forEach var="address" items="${pupil.person.addresses}" varStatus="status">
                    <div class="field" id="addresses${status.index}Field" data-index="${status.index}">
                        <form:hidden path="person.addresses[${status.index}].id"/>

                        <form:select path="person.addresses[${status.index}].addressType"
                                     items="${addressTypeEnum}"/>
                        <button class="negative" type="button"
                                onclick="ivis.ui.removeContainer('addresses${status.index}Field');">Remove
                        </button>
                            <%--<input type="hidden" name="person.addresses[${status.index}].addressType" value="${address.addressType}"/>--%>

                        <form:label path="person.addresses[${status.index}].careOf">c/o</form:label>
                        <form:input path="person.addresses[${status.index}].careOf" cssErrorClass="error"/>
                        <form:errors path="person.addresses[${status.index}].careOf" cssClass="error-description"/>

                        <form:label path="person.addresses[${status.index}].street">Street</form:label>
                        <form:input path="person.addresses[${status.index}].street" cssErrorClass="error"/>
                        <form:errors path="person.addresses[${status.index}].street" cssClass="error-description"/>

                        <form:label path="person.addresses[${status.index}].postalCode">Postal code</form:label>
                        <form:input path="person.addresses[${status.index}].postalCode" cssErrorClass="error"/>
                        <form:errors path="person.addresses[${status.index}].postalCode" cssClass="error-description"/>

                        <form:label path="person.addresses[${status.index}].city">City</form:label>
                        <form:input path="person.addresses[${status.index}].city" cssErrorClass="error"/>
                        <form:errors path="person.addresses[${status.index}].city" cssClass="error-description"/>

                        <form:label
                                path="person.addresses[${status.index}].municipalityCode">Municipality code</form:label>
                        <form:input path="person.addresses[${status.index}].municipalityCode" cssErrorClass="error"/>
                        <form:errors path="person.addresses[${status.index}].municipalityCode"
                                     cssClass="error-description"/>
                    </div>
                </c:forEach>
                <button class="positive" type="button" onclick="ivis.ui.addAddress();">Add</button>
            </div>

            <h2>Phones:</h2>
            <div id="phones">
                <c:forEach var="phone" items="${pupil.person.phones}" varStatus="status">
                    <div class="field" id="phones${status.index}Field" data-index="${status.index}">
                        <form:hidden path="person.phones[${status.index}].id"/>

                            <%--<input type="hidden" name="person.phones[${status.index}].communicationType" value="${phone.communicationType}"/>--%>
                        <form:select path="person.phones[${status.index}].communicationType"
                                     items="${communicationTypeEnum}"/>
                        <button class="negative" type="button"
                                onclick="ivis.ui.removeContainer('phones${status.index}Field');">Remove
                        </button>
                        <form:label path="person.phones[${status.index}].number">Phone</form:label>
                        <form:input path="person.phones[${status.index}].number" cssErrorClass="error"/>
                        <form:errors path="person.phones[${status.index}].number" cssClass="error-description"/>
                    </div>
                </c:forEach>
                <button class="positive" type="button" onclick="ivis.ui.addPhone();">Add</button>
            </div>

            <h2>Emails:</h2>

            <div id="emails">
                <c:forEach var="email" items="${pupil.person.emails}" varStatus="status">
                    <div class="field" id="emails${status.index}Field" data-index="${status.index}">
                        <form:hidden path="person.emails[${status.index}].id"/>

                        <form:select path="person.emails[${status.index}].communicationType"
                                     items="${communicationTypeEnum}"/>
                        <button class="negative" type="button"
                                onclick="ivis.ui.removeContainer('emails${status.index}Field');">Remove
                        </button>
                        <form:label
                                path="person.emails[${status.index}].address">Email</form:label>
                        <form:input path="person.emails[${status.index}].address" cssErrorClass="error"/>
                        <form:errors path="person.emails[${status.index}].address" cssClass="error-description"/>
                    </div>
                </c:forEach>
                <button class="positive" type="button" onclick="ivis.ui.addEmail();">Add</button>
            </div>
            <div class="buttons">
                <button class="positive" type="submit">Save</button>
                <a class="button neutral" type="/pupils">Cancel</a>
            </div>
        </div>
        <div id="guardiansTabPage" class="tab-page">
            2
        </div>
        <div id="schoolAndClassTabPage" class="tab-page">
            3
        </div>
        <div id="schoolTransportTabPage" class="tab-page">
            4
        </div>
        <div id="loggTabPage" class="tab-page">
            5
        </div>
    </form:form>
</c:if>
<script type="text/javascript">
    $(document).ready(
            function () {
                initialize();
            }
    );
    var communicationTypeEnum = [<%
        Enum[] values = CommunicationTypeEnum.values();

        for(int i = 0; i < values.length; i++) {
          Enum typeEnum = values[i];
          out.print("{name:\"");
          out.print(typeEnum.name());
          out.print("\", description:\"");
          out.print(typeEnum.toString());
          out.print("\"}, ");
        }
        %>];

    var addressTypeEnum = [<%
        values = AddressTypeEnum.values();

        for(int i = 0; i < values.length; i++) {
          Enum typeEnum = values[i];
          out.print("{name:\"");
          out.print(typeEnum.name());
          out.print("\", description:\"");
          out.print(typeEnum.toString());
          out.print("\"}, ");
        }
        %>];
</script>
<jsp:include page="ivis_footer.jsp"/>