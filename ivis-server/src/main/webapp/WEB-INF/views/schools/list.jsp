<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.school.list" var="labelListName"/>

<c:set value="/schools" var="mainPath"/>
<spring:url value="${mainPath}" var="mainUrl"/>
<spring:url value="${mainPath}/?form" var="addUrl"/>
<h1>${labelListName}</h1>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th class="ordered-by">Name</th>
        <th>School ID</th>
        <th>Services</th>
        <th>Next Cloud Enabled</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:if test="${not empty entityList}">
        <tbody><c:forEach items="${entityList}" var="embeded">
            <tr data-object-id="${embeded.id}">
                <td>${embeded.id}</td>
                <td>${embeded.name}</td>
                <td>${embeded.schoolId}</td>
                <td>
                    <c:forEach items="${embeded.services}" var="service" varStatus="fileOptionStatus">
                        ${service}
                        <c:if test="${!fileOptionStatus.last}">, </c:if>
                    </c:forEach>
                </td>
                <td>${embeded.nextCloudEnabled}</td>
                <td class="buttons">
                    <a class="button positive" href="${mainUrl}/${embeded.id}?form">Edit</a>
                    <button class="negative" type="button" onclick="deleteElement('${mainUrl}',${embeded.id})">Remove</button>
                </td>
            </tr>
        </c:forEach></tbody>
    </c:if>
</table>

<div class="buttons">
    <a class="button positive" href="${addUrl}">Add</a>
</div>
