<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="/pupils" var="mainPath"/>
<spring:url value="${mainPath}" var="mainUrl"/>
<spring:url value="${mainPath}" var="backUrl"/>
<h1>Edit Pupil </h1>
<%--<div class="tabs">--%>
    <%--<div class="tab" data-tab-page-id="basicDataTabPage">--%>
        <%--Basic data--%>
    <%--</div>--%>
    <%--<div class="tab" data-tab-page-id="guardiansTabPage">--%>
        <%--Guardians--%>
    <%--</div>--%>
    <%--<div class="tab" data-tab-page-id="schoolAndClassTabPage">--%>
        <%--School & class--%>
    <%--</div>--%>
    <%--<div class="tab" data-tab-page-id="schoolTransportTabPage">--%>
        <%--School transport--%>
    <%--</div>--%>
    <%--<div class="tab" data-tab-page-id="loggTabPage">--%>
        <%--Logg--%>
    <%--</div>--%>
<%--</div>--%>
<form:form modelAttribute="entity" id="entityUpdateForm" method="post">
    <div id="entity.pupil.personField">
    <c:set var="personPath" value="person"/>
    <c:set var="person" value="${entity.person}"/>
    <input id="person" type="hidden" name="${personPath}.id" value="${person.id}">

    <div class="field">
        <form:label path="${personPath}.personalId">Personal ID</form:label>
        <form:input data-rule-maxlength="255" path="${personPath}.personalId" cssErrorClass="error"/>
        <form:errors path="${personPath}.personalId" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="${personPath}.lastName">Last name</form:label>
        <form:input data-rule-maxlength="255" path="${personPath}.lastName" cssErrorClass="error"/>
        <form:errors path="${personPath}.lastName" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="${personPath}.firstName">
            First name
        </form:label>
        <form:input data-rule-maxlength="255" path="${personPath}.firstName" cssErrorClass="error"/>
        <form:errors path="${personPath}.firstName" cssClass="error-description"/>
    </div>
    </div>

    <%--<div id="schoolAndClassTabPage" class="tab-page">--%>
    <%--<div class="field">--%>
        <%--<form:label path="school">School</form:label>--%>
        <%--<form:select path="school" items="${schoolList}" itemValue="id" itemLabel="name" onchange="ivis.ui.onSchoolChange(this.value);"/>--%>
        <%--<form:errors path="school" cssClass="error-description"/>--%>
    <%--</div>--%>
    <%--<div class="field">--%>
        <%--<form:label path="academicYear">Academic year</form:label>--%>
        <%--<form:select path="academicYear" items="${academicYearList}" itemLabel="name" itemValue="id"/>--%>
        <%--<form:errors path="academicYear" cssClass="error-description"/>--%>
    <%--</div>--%>
    <%--<div class="field">--%>
        <%--<form:label path="schoolClass">Academic year</form:label>--%>
        <%--<form:select path="schoolClass" items="${schoolClassList}" itemLabel="name" itemValue="id"/>--%>
        <%--<form:errors path="schoolClass" cssClass="error-description"/>--%>
    <%--</div>--%>

    <%--<div class="field">--%>
        <%--<form:label path="classPlacementFrom">Class placement from</form:label>--%>
        <%--<fmt:formatDate value="${entity.classPlacementFrom}" var="dateString" pattern="yyyy-MM-dd"/>--%>
        <%--<input id="classPlacementFrom" name="classPlacementFrom" type="date" value="${dateString}">--%>
        <%--<form:errors path="classPlacementFrom" cssClass="error-description"/>--%>

        <%--<form:label path="classPlacementTo">to</form:label>--%>
        <%--<fmt:formatDate value="${entity.classPlacementTo}" var="dateString" pattern="yyyy-MM-dd"/>--%>
        <%--<input id="classPlacementTo" name="classPlacementTo" type="date" value="${dateString}">--%>
        <%--<form:errors path="classPlacementTo" cssClass="error-description"/>--%>
    <%--</div>--%>

    <%--<h2>School days, start and end time</h2>--%>

    <%--<c:if test="${not empty pupil.schoolClass and not empty pupil.schoolClass.diaries}">--%>
        <%--<table cellpadding="0" cellspacing="0" class="field">--%>
            <%--<thead>--%>
            <%--<tr>--%>
                <%--<th>&nbsp;</th>--%>
                <%--<th>Start</th>--%>
                <%--<th>End</th>--%>
            <%--</tr>--%>
            <%--</thead>--%>
            <%--<tbody>--%>
            <%--<c:forEach var="afterSchoolSchema" items="${pupil.schoolClass.diaries}">--%>
                <%--<tr>--%>
                    <%--<td>${afterSchoolSchema.dayOfWeek}</td>--%>
                    <%--<td><fmt:formatDate value="${afterSchoolSchema.startTime}" type="time"--%>
                                        <%--pattern="HH:mm"/></td>--%>
                    <%--<td><fmt:formatDate value="${afterSchoolSchema.endTime}" type="both" pattern="HH:mm"/></td>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
            <%--</tbody>--%>
        <%--</table>--%>
    <%--</c:if>--%>
    <%--&lt;%&ndash;AFTER SCHOOL CENTER&ndash;%&gt;--%>
    <%--<div id="afterSchoolCenterContainer">--%>
        <%--<div class="checkbox">--%>
                <%--&lt;%&ndash;<c:set var="hasAfterSchoolCenter" value="${not empty pupil.afterSchoolCenterSection}"/>&ndash;%&gt;--%>
            <%--<input id="hasAfterSchoolCenter" type="checkbox" ${hasAfterSchoolCenter ? 'checked': ''} onclick="ivis.ui.toggleDiv('afterSchoolCenterDetails');">--%>
                <%--&lt;%&ndash;onchange="ivis.ui.disableContactPerson('hasContactPerson', 'pupil.contactPersonField');">&ndash;%&gt;--%>
            <%--<label for="hasAfterSchoolCenter">After school center</label>--%>
        <%--</div>--%>
        <%--<div id="afterSchoolCenterDetails">--%>
            <%--<div class="field">--%>
                <%--<form:label--%>
                        <%--path="afterSchoolCenterSection">Name</form:label>--%>
                <%--<form:select path="afterSchoolCenterSection" items="${afterSchoolCenterSectionSet}"--%>
                             <%--itemLabel="name" itemValue="id"/>--%>
            <%--</div>--%>
            <%--<table cellpadding="0" cellspacing="0" class="field">--%>
                <%--<thead>--%>
                <%--<tr>--%>
                    <%--<th>&nbsp;</th>--%>
                    <%--<th>Before</th>--%>
                    <%--<th>After</th>--%>
                <%--</tr>--%>
                <%--</thead>--%>
                <%--<tbody>--%>
                <%--<c:forEach var="afterSchoolSchema" items="${afterSchoolCenterSchema}" varStatus="status">--%>
                    <%--<tr>--%>
                        <%--<td>--%>
                                <%--${afterSchoolSchema.dayOfWeek}--%>
                            <%--<input type="hidden" name="schoolCenterSchema[${status.index}].dayOfWeek"--%>
                                   <%--value="${afterSchoolSchema.dayOfWeek}">--%>
                        <%--</td>--%>
                        <%--<td><input type="checkbox"--%>
                                   <%--name="schoolCenterSchema[${status.index}].useBeforeSchool" ${afterSchoolSchema.isUseBeforeSchool() ? "checked": ""}>--%>
                        <%--</td>--%>
                        <%--<td><input type="checkbox"--%>
                                   <%--name="schoolCenterSchema[${status.index}].useAfterSchool" ${afterSchoolSchema.isUseAfterSchool() ? "checked": ""}>--%>
                        <%--</td>--%>
                            <%--&lt;%&ndash;<td>${afterSchoolSchema.isUseAfterSchool()}</td>&ndash;%&gt;--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                <%--</tbody>--%>
            <%--</table>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--</div>--%>


    <%--<c:if test="${not empty message}">--%>
    <%--<div id="message" class="${message.type}">${message.message}</div>--%>
    <%--</c:if>--%>
    <%--<div class="checkbox">--%>
        <%--<form:checkbox path="enabled" value="true" cssErrorClass="error" label="Enabled"/>--%>
        <%--<form:errors path="enabled" cssClass="error-description"/>--%>
    <%--</div>--%>

    <%--<div class="field">--%>
        <%--<form:label path="name">--%>
            <%--Name*--%>
        <%--</form:label>--%>
        <%--<form:input path="name" cssErrorClass="error"/>--%>
        <%--<form:errors path="name" cssClass="error-description"/>--%>
    <%--</div>--%>

    <%--<div class="field">--%>
        <%--<form:label path="schoolId">--%>
            <%--School ID*--%>
        <%--</form:label>--%>
        <%--<form:input path="schoolId"/>--%>
        <%--<form:errors path="schoolId" cssClass="error"/>--%>
    <%--</div>--%>

    <%--<div class="field">--%>
        <%--<form:label path="services">--%>
            <%--Services*--%>
        <%--</form:label>--%>
        <%--<form:select path="services" items="${serviceTypeList}" multiple="true" cssErrorClass="error">--%>
        <%--</form:select>--%>
        <%--<form:errors path="services" cssClass="error-description"/>--%>
    <%--</div>--%>
<%--&lt;%&ndash;===================================================SchoolClasses=========================================================&ndash;%&gt;--%>
    <%--<c:set value="/schoolclasses" var="embededPath"/>--%>
    <%--<spring:url value="${embededPath}" var="embededUrl"/>--%>
    <%--<spring:url value="${embededPath}/?form" var="addUrl"/>--%>
    <%--<h2>School classes</h2>--%>

    <%--<table cellpadding="0" cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Id</th>--%>
            <%--<th class="ordered-by">Name</th>--%>
            <%--&lt;%&ndash;<th>School ID</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th>Services</th>&ndash;%&gt;--%>
            <%--<th>&nbsp;</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<c:if test="${not empty entity.schoolClasses}">--%>
            <%--<tbody><c:forEach items="${entity.schoolClasses}" var="embeded">--%>
                <%--<tr data-object-id="${embeded.id}">--%>
                    <%--<td>${embeded.id}</td>--%>
                    <%--<td>${embeded.name}</td>--%>
                    <%--&lt;%&ndash;<td>${embeded.schoolId}</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:forEach items="${embeded.services}" var="service" varStatus="status">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;${service}&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<c:if test="${!status.last}">, </c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td class="buttons">&ndash;%&gt;--%>
                        <%--<a class="button positive" href="${embededUrl}/${embeded.id}?form">Edit</a>--%>
                        <%--&lt;%&ndash;<button class="negative" type="button" onclick="deleteElement('${embededUrl}',${embeded.id})">Remove</button>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                <%--</tr>--%>
            <%--</c:forEach></tbody>--%>
        <%--</c:if>--%>
    <%--</table>--%>

    <%--&lt;%&ndash;<div class="buttons">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<a class="button positive" href="${addUrl}">Add</a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>


<%--&lt;%&ndash;==============================================AfterSchoolCenterSections==================================================&ndash;%&gt;--%>
    <%--<c:set value="/afterschoolcentersections" var="embededPath"/>--%>
    <%--<spring:url value="${embededPath}" var="embededUrl"/>--%>
    <%--<spring:url value="${embededPath}/?form" var="addUrl"/>--%>
    <%--<h2>Afrer school section</h2>--%>

    <%--<table cellpadding="0" cellspacing="0">--%>
        <%--<thead>--%>
        <%--<tr>--%>
            <%--<th>Id</th>--%>
            <%--<th class="ordered-by">Name</th>--%>
                <%--&lt;%&ndash;<th>School ID</th>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<th>Services</th>&ndash;%&gt;--%>
            <%--<th>&nbsp;</th>--%>
        <%--</tr>--%>
        <%--</thead>--%>
        <%--<c:if test="${not empty entity.afterSchoolCenterSections}">--%>
            <%--<tbody><c:forEach items="${entity.afterSchoolCenterSections}" var="embeded">--%>
                <%--<tr data-object-id="${embeded.id}">--%>
                    <%--<td>${embeded.id}</td>--%>
                    <%--<td>${embeded.name}</td>--%>
                        <%--&lt;%&ndash;<td>${embeded.schoolId}</td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:forEach items="${embeded.services}" var="service" varStatus="status">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;${service}&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<c:if test="${!status.last}">, </c:if>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<td class="buttons">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<a class="button positive" href="${embededUrl}/${embeded.id}?form">Edit</a>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<button class="negative" type="button" onclick="deleteElement('${embededUrl}',${embeded.id})">Remove</button>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                <%--</tr>--%>
            <%--</c:forEach></tbody>--%>
        <%--</c:if>--%>
    <%--</table>--%>

    <%--&lt;%&ndash;<div class="buttons">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<a class="button positive" href="${addUrl}">Add</a>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>



    <%--&lt;%&ndash;=========================================================================================================================&ndash;%&gt;--%>
    <%--&lt;%&ndash;<sec:authorize access="hasRole('ROLE_ADMIN')">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<div class="field">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:label path="password">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Password*&ndash;%&gt;--%>
            <%--&lt;%&ndash;</form:label>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:input path="password" id="password" cssErrorClass="error"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:errors path="password" cssClass="error-description"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;&lt;%&ndash;<div class="field">&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<form:label path="confirmPassword">&ndash;%&gt;&ndash;%&gt;--%>
                <%--&lt;%&ndash;&lt;%&ndash;Confirm password*&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</form:label>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:hidden path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:errors path="confirmPassword" cssClass="error-description"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;&lt;%&ndash;</div>&ndash;%&gt;&ndash;%&gt;--%>
    <%--&lt;%&ndash;</sec:authorize>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<sec:authorize access="!hasRole('ROLE_ADMIN')">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div class="field">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:label path="password">&ndash;%&gt;--%>
            <%--&lt;%&ndash;Password*&ndash;%&gt;--%>
        <%--&lt;%&ndash;</form:label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:password path="password" id="password" cssErrorClass="error"/>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<form:errors path="password" cssClass="error-description"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<div class="field">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:label path="confirmPassword">&ndash;%&gt;--%>
            <%--&lt;%&ndash;Confirm password*&ndash;%&gt;--%>
        <%--&lt;%&ndash;</form:label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:password path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:errors path="confirmPassword" cssClass="error-description"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</sec:authorize>&ndash;%&gt;--%>

    <%--&lt;%&ndash;<div class="checkbox">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:label path="authorities">&ndash;%&gt;--%>
            <%--&lt;%&ndash;Roles*&ndash;%&gt;--%>
        <%--&lt;%&ndash;</form:label>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:checkboxes path="authorities" items="${roleList}" itemLabel="authority" cssErrorClass="error"&ndash;%&gt;--%>
                         <%--&lt;%&ndash;cssClass="check-box" itemValue="id"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:errors path="authorities" cssClass="error-description"/>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <div class="buttons">
        <button class="positive" type="submit">Save</button>
        <a class="button neutral" href="${backUrl}">Back</a>
    </div>
</form:form>
