<%@ page import="com.imcode.entities.Pupil,
                 com.imcode.services.PupilService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.IvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="com.imcode.entities.enums.AddressTypeEnum" %>
<%@ page import="com.imcode.entities.enums.CommunicationTypeEnum" %>
<%@ page import="com.imcode.services.SchoolService" %>
<%@ page import="com.imcode.entities.School" %>
<%@ page import="com.imcode.services.SchoolClassService" %>
<%@ page import="com.imcode.entities.SchoolClass" %>
<%@ page import="com.imcode.services.AcademicYearService" %>
<%@ page import="java.util.*" %>

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
        PupilService pupilService = factory.getService(PupilService.class);
        Pupil pupil = new Pupil();

        SchoolService schoolService = factory.getService(SchoolService.class);
        List<School> schoolList = schoolService.findAll();
        request.setAttribute("schoolList", schoolList);

//        SchoolClassService schoolClassService = factory.getService(SchoolClassService.class);
//        List<SchoolClass> schoolClassList = schoolClassService.findAll();
//        request.setAttribute("schoolClassList", schoolClassList);

        AcademicYearService academicYearService = factory.getService(AcademicYearService.class);
        request.setAttribute("academicYearList", academicYearService.findAll());

//        Service  = factory.getService();
//        request.setAttribute("List", .findAll());

        Long id = null;

        try {
            id = Long.valueOf(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (id != null) {
            try {
                pupil = pupilService.find(Long.valueOf(id));
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(session, null);
                response.sendRedirect(Imcms.getServerProperties().getProperty("ClientAddress") + "/servlet/StartDoc?meta_id=" + viewing.getTextDocument().getId());
                return;
            }
        }
        request.setAttribute("pupil", pupil);
        Set<SchoolClass> schoolClassList = new HashSet<SchoolClass>();

        try { schoolClassList = pupil.getSchool().getSchoolClasses(); } catch (Exception ignore) { }

        request.setAttribute("schoolClassList", schoolClassList);
    }

    request.setAttribute("communicationTypeEnum", Arrays.asList(CommunicationTypeEnum.values()));
    request.setAttribute("addressTypeEnum", AddressTypeEnum.values());

%>
<c:if test="${not empty pupil}">
    <h1>Pupil - ${pupil.person.firstName} ${pupil.person.lastName}</h1>

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
    <form:form modelAttribute="pupil" action="${clientAddress}/api/content/ivis/pupils" method="post">
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
                            <%--<form:hidden path="person.addresses[${status.index}].id"/>--%>

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
                            <%--<form:hidden path="person.phones[${status.index}].id"/>--%>

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
                            <%--<form:hidden path="person.emails[${status.index}].id"/>--%>

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
            <c:forEach var="guardian" items="${pupil.guardians}" varStatus="status">
                <c:set var="guardianItemId" value="guardians${status.index}"/>
                <c:set var="guardianItemName" value="guardians[${status.index}]"/>
                <h2>Guardian ${status.index + 1}</h2>
                <form:hidden path="${guardianItemName}.id" cssErrorClass="error"/>

                <form:hidden path="${guardianItemName}.person.id" cssErrorClass="error"/>

                <div class="field">
                    <form:label path="${guardianItemName}.person.personalId">Personal ID</form:label>
                    <form:input path="${guardianItemName}.person.personalId" cssErrorClass="error"/>
                    <form:errors path="${guardianItemName}.person.personalId" cssClass="error-description"/>
                </div>

                <div class="field">
                    <form:label path="${guardianItemName}.person.lastName">Last name</form:label>
                    <form:input path="${guardianItemName}.person.lastName" cssErrorClass="error"/>
                    <form:errors path="${guardianItemName}.person.lastName" cssClass="error-description"/>
                </div>

                <div class="field">
                    <form:label path="${guardianItemName}.person.firstName">
                        First name
                    </form:label>
                    <form:input path="${guardianItemName}.person.firstName" cssErrorClass="error"/>
                    <form:errors path="${guardianItemName}.person.firstName" cssClass="error-description"/>
                </div>

                <h2>Addresses:</h2>

                <div id="${guardianItemId}addresses">
                    <c:forEach var="address" items="${guardian.person.addresses}" varStatus="status">
                        <div class="field" id="${guardianItemId}addresses${status.index}Field"
                             data-index="${status.index}">
                                <%--<form:hidden path="person.addresses[${status.index}].id"/>--%>

                            <form:select path="${guardianItemName}.person.addresses[${status.index}].addressType"
                                         items="${addressTypeEnum}"/>
                                <%--<button class="negative" type="button"--%>
                                <%--onclick="ivis.ui.removeContainer('addresses${status.index}Field');">Remove--%>
                                <%--</button>--%>
                                <%--&lt;%&ndash;<input type="hidden" name="person.addresses[${status.index}].addressType" value="${address.addressType}"/>&ndash;%&gt;--%>

                            <form:label
                                    path="${guardianItemName}.person.addresses[${status.index}].careOf">c/o</form:label>
                            <form:input path="${guardianItemName}.person.addresses[${status.index}].careOf"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.addresses[${status.index}].careOf"
                                         cssClass="error-description"/>

                            <form:label
                                    path="${guardianItemName}.person.addresses[${status.index}].street">Street</form:label>
                            <form:input path="${guardianItemName}.person.addresses[${status.index}].street"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.addresses[${status.index}].street"
                                         cssClass="error-description"/>

                            <form:label
                                    path="${guardianItemName}.person.addresses[${status.index}].postalCode">Postal code</form:label>
                            <form:input path="${guardianItemName}.person.addresses[${status.index}].postalCode"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.addresses[${status.index}].postalCode"
                                         cssClass="error-description"/>

                            <form:label
                                    path="${guardianItemName}.person.addresses[${status.index}].city">City</form:label>
                            <form:input path="${guardianItemName}.person.addresses[${status.index}].city"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.addresses[${status.index}].city"
                                         cssClass="error-description"/>

                            <form:label
                                    path="${guardianItemName}.person.addresses[${status.index}].municipalityCode">Municipality code</form:label>
                            <form:input path="${guardianItemName}.person.addresses[${status.index}].municipalityCode"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.addresses[${status.index}].municipalityCode"
                                         cssClass="error-description"/>
                        </div>
                    </c:forEach>
                        <%--<button class="positive" type="button" onclick="ivis.ui.addAddress();">Add</button>--%>
                </div>

                <h2>Contact information:</h2>

                <div id="${guardianItemId}phones">
                    <c:forEach var="phone" items="${guardian.person.phones}" varStatus="status">
                        <div class="field" id="${guardianItemId}phones${status.index}Field"
                             data-index="${status.index}">
                                <%--<form:hidden path="person.phones[${status.index}].id"/>--%>

                                <%--<input type="hidden" name="person.phones[${status.index}].communicationType" value="${phone.communicationType}"/>--%>
                            <form:select path="${guardianItemName}.person.phones[${status.index}].communicationType"
                                         items="${communicationTypeEnum}"/>
                                <%--<button class="negative" type="button"--%>
                                <%--onclick="ivis.ui.removeContainer('phones${status.index}Field');">Remove--%>
                                <%--</button>--%>
                            <form:label
                                    path="${guardianItemName}.person.phones[${status.index}].number">Phone</form:label>
                            <form:input path="${guardianItemName}.person.phones[${status.index}].number"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.phones[${status.index}].number"
                                         cssClass="error-description"/>
                        </div>
                    </c:forEach>
                        <%--<button class="positive" type="button" onclick="ivis.ui.addPhone();">Add</button>--%>
                </div>

                <%--<h2>Emails:</h2>--%>

                <div id="${guardianItemId}emails">
                    <c:forEach var="email" items="${guardian.person.emails}" varStatus="status">
                        <div class="field" id="${guardianItemId}emails${status.index}Field"
                             data-index="${status.index}">
                                <%--<form:hidden path="person.emails[${status.index}].id"/>--%>

                            <form:select path="${guardianItemName}.person.emails[${status.index}].communicationType"
                                         items="${communicationTypeEnum}"/>
                                <%--<button class="negative" type="button"--%>
                                <%--onclick="ivis.ui.removeContainer('emails${status.index}Field');">Remove--%>
                                <%--</button>--%>
                            <form:label
                                    path="${guardianItemName}.person.emails[${status.index}].address">Email</form:label>
                            <form:input path="${guardianItemName}.person.emails[${status.index}].address"
                                        cssErrorClass="error"/>
                            <form:errors path="${guardianItemName}.person.emails[${status.index}].address"
                                         cssClass="error-description"/>
                        </div>
                    </c:forEach>
                        <%--<button class="positive" type="button" onclick="ivis.ui.addEmail();">Add</button>--%>
                </div>
            </c:forEach>
            <div class="buttons">
                <button class="positive" type="submit">Save</button>
                <a class="button neutral" type="/pupils">Cancel</a>
            </div>
        </div>
        <div id="schoolAndClassTabPage" class="tab-page">
            <div class="field">
                <form:select path="school" items="${schoolList}" itemValue="id" itemLabel="name" onchange="ivis.ui.onSchoolChange(this.value);"/>
                <label>School ID</label>
                <label id="schoolIdLabel">${pupil.school.schoolId}</label>
            </div>
            <div class="field">
                <form:select path="academicYear" items="${academicYearList}" itemLabel="name" itemValue="id"/>
            </div>
            <div class="field">
                <form:select path="schoolClass" items="${schoolClassList}" itemLabel="name" itemValue="id"/>
            </div>

            <div class="buttons">
                <button class="positive" type="submit">Save</button>
                <a class="button neutral" type="/pupils">Cancel</a>
            </div>
        </div>
        <div id="schoolTransportTabPage" class="tab-page">
            <div class="buttons">
                <button class="positive" type="submit">Save</button>
                <a class="button neutral" type="/pupils">Cancel</a>
            </div>
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