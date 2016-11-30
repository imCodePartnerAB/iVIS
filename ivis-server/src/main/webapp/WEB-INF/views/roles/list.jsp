<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.roles.list" var="labelListName"/>

<c:set value="/roles" var="mainPath"/>
<spring:url value="${mainPath}" var="mainUrl"/>
<spring:url value="${mainPath}/?form" var="addUrl"/>
<h1>${labelListName}</h1>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>For</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:if test="${not empty entityList}">
        <tbody><c:forEach items="${entityList}" var="role">
            <tr data-object-id="${role.id}">
                <td>${role.id}</td>
                <td>${role.name}</td>
                <td><c:out value="${role.userRole ? 'USERS': 'CLIENTS'}"/></td>
                <td class="buttons">
                    <a class="button positive" href="${mainUrl}/${role.id}?form">Edit</a>
                    <button class="negative" type="button" onclick="deleteElement('${mainUrl}',${role.id})">Remove</button>
                </td>
            </tr>
        </c:forEach></tbody>
    </c:if>
</table>

<div class="buttons">
    <a class="button positive" href="${addUrl}">Add</a>
</div>
