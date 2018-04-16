<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set value="/schools" var="mainPath"/>
<spring:url value="${mainPath}" var="mainUrl"/>
<spring:url value="${mainPath}" var="backUrl"/>
<spring:url value="/schoolClasses" var="schoolClassesUrl"/>
<h1>Edit School</h1>
<form:form modelAttribute="entity" id="schoolUpdateForm" method="post">
    <%--<c:if test="${not empty message}">--%>
    <%--<div id="message" class="${message.type}">${message.message}</div>--%>
    <%--</c:if>--%>
    <%--<div class="checkbox">--%>
        <%--<form:checkbox path="enabled" value="true" cssErrorClass="error" label="Enabled"/>--%>
        <%--<form:errors path="enabled" cssClass="error-description"/>--%>
    <%--</div>--%>

    <div class="checkbox">
        <form:checkbox path="schoolCloudEnabled" value="true" cssErrorClass="error" label="School Cloud Enabled"/>
        <form:errors path="schoolCloudEnabled" cssClass="error-description"/>
    </div>

    <div class="field">
        <form:label path="name">
            Name*
        </form:label>
        <form:input path="name" cssErrorClass="error"/>
        <form:errors path="name" cssClass="error-description"/>
    </div>

    <div class="field">
        <form:label path="schoolId">
            School ID*
        </form:label>
        <form:input path="schoolId"/>
        <form:errors path="schoolId" cssClass="error"/>
    </div>

    <div class="field">
        <form:label path="services">
            Services*
        </form:label>
        <form:select path="services" items="${serviceTypeList}" multiple="true" cssErrorClass="error">
        </form:select>
        <form:errors path="services" cssClass="error-description"/>
    </div>
<%--===================================================SchoolClasses=========================================================--%>
    <c:set value="/schoolclasses" var="embededPath"/>
    <spring:url value="${embededPath}" var="embededUrl"/>
    <spring:url value="${embededPath}/?form" var="addUrl"/>
    <h2>School classes</h2>

    <table cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th class="ordered-by">Name</th>
            <th>School Cloud Enabled</th>
            <%--<th>School ID</th>--%>
            <%--<th>Services</th>--%>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <c:if test="${not empty entity.schoolClasses}">
            <tbody><c:forEach items="${entity.schoolClasses}" var="embeded">
                <tr data-object-id="${embeded.id}">
                    <td>${embeded.id}</td>
                    <td>${embeded.name}</td>
                    <td>${embeded.schoolCloudEnabled}</td>
                    <td class="buttons">
                        <a class="button positive" href="${schoolClassesUrl}/${embeded.id}?form">Edit</a>
                    </td>
                    <%--<td>${embeded.schoolId}</td>--%>
                    <%--<td>--%>
                        <%--<c:forEach items="${embeded.services}" var="service" varStatus="status">--%>
                            <%--${service}--%>
                            <%--<c:if test="${!status.last}">, </c:if>--%>
                        <%--</c:forEach>--%>
                    <%--</td>--%>
                    <%--<td class="buttons">--%>
                        <%--<a class="button positive" href="${embededUrl}/${embeded.id}?form">Edit</a>--%>
                        <%--<button class="negative" type="button" onclick="deleteElement('${embededUrl}',${embeded.id})">Remove</button>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach></tbody>
        </c:if>
    </table>

    <%--<div class="buttons">--%>
        <%--<a class="button positive" href="${addUrl}">Add</a>--%>
    <%--</div>--%>


<%--==============================================AfterSchoolCenterSections==================================================--%>
    <c:set value="/afterschoolcentersections" var="embededPath"/>
    <spring:url value="${embededPath}" var="embededUrl"/>
    <spring:url value="${embededPath}/?form" var="addUrl"/>
    <h2>Afrer school section</h2>

    <table cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th class="ordered-by">Name</th>
                <%--<th>School ID</th>--%>
                <%--<th>Services</th>--%>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <c:if test="${not empty entity.afterSchoolCenterSections}">
            <tbody><c:forEach items="${entity.afterSchoolCenterSections}" var="embeded">
                <tr data-object-id="${embeded.id}">
                    <td>${embeded.id}</td>
                    <td>${embeded.name}</td>
                        <%--<td>${embeded.schoolId}</td>--%>
                        <%--<td>--%>
                        <%--<c:forEach items="${embeded.services}" var="service" varStatus="status">--%>
                        <%--${service}--%>
                        <%--<c:if test="${!status.last}">, </c:if>--%>
                        <%--</c:forEach>--%>
                        <%--</td>--%>
                    <%--<td class="buttons">--%>
                        <%--<a class="button positive" href="${embededUrl}/${embeded.id}?form">Edit</a>--%>
                        <%--<button class="negative" type="button" onclick="deleteElement('${embededUrl}',${embeded.id})">Remove</button>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach></tbody>
        </c:if>
    </table>

    <%--<div class="buttons">--%>
        <%--<a class="button positive" href="${addUrl}">Add</a>--%>
    <%--</div>--%>



    <%--=========================================================================================================================--%>
    <%--<sec:authorize access="hasRole('ROLE_ADMIN')">--%>
        <%--<div class="field">--%>
            <%--<form:label path="password">--%>
                <%--Password*--%>
            <%--</form:label>--%>
            <%--<form:input path="password" id="password" cssErrorClass="error"/>--%>
            <%--<form:errors path="password" cssClass="error-description"/>--%>
        <%--</div>--%>
        <%--&lt;%&ndash;<div class="field">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form:label path="confirmPassword">&ndash;%&gt;--%>
                <%--&lt;%&ndash;Confirm password*&ndash;%&gt;--%>
            <%--&lt;%&ndash;</form:label>&ndash;%&gt;--%>
            <%--<form:hidden path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>--%>
            <%--<form:errors path="confirmPassword" cssClass="error-description"/>--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--</sec:authorize>--%>
    <%--<sec:authorize access="!hasRole('ROLE_ADMIN')">--%>
    <%--<div class="field">--%>
        <%--<form:label path="password">--%>
            <%--Password*--%>
        <%--</form:label>--%>
        <%--<form:password path="password" id="password" cssErrorClass="error"/>--%>

        <%--<form:errors path="password" cssClass="error-description"/>--%>
    <%--</div>--%>
    <%--<div class="field">--%>
        <%--<form:label path="confirmPassword">--%>
            <%--Confirm password*--%>
        <%--</form:label>--%>
        <%--<form:password path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>--%>
        <%--<form:errors path="confirmPassword" cssClass="error-description"/>--%>
    <%--</div>--%>
    <%--</sec:authorize>--%>

    <%--<div class="checkbox">--%>
        <%--<form:label path="authorities">--%>
            <%--Roles*--%>
        <%--</form:label>--%>
        <%--<form:checkboxes path="authorities" items="${roleList}" itemLabel="authority" cssErrorClass="error"--%>
                         <%--cssClass="check-box" itemValue="id"/>--%>
        <%--<form:errors path="authorities" cssClass="error-description"/>--%>
    <%--</div>--%>
    <div class="buttons">
        <button class="positive" type="submit">Save</button>
        <a class="button neutral" href="${backUrl}">Back</a>
    </div>
</form:form>
