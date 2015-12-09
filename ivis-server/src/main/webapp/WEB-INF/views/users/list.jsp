<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.user.list" var="labelUserList"/>
<spring:url value="/users/?form" var="addUrl"/>
<spring:url value="/users" var="listUrl"/>
<h1>${labelUserList}</h1>

<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th class="ordered-by">Name</th>
        <th>Roles</th>
        <th>Enabled</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:if test="${not empty userList}">
        <tbody><c:forEach items="${userList}" var="embeded">
            <tr data-object-id="${embeded.id}">
                <td>${embeded.id}</td>
                <td>${embeded.name}</td>
                <td>
                    <c:forEach items="${embeded.authorities}" var="role" varStatus="fileOptionStatus">
                        ${role.name}
                        <c:if test="${!fileOptionStatus.last}">, </c:if>
                    </c:forEach>
                </td>
                <td>${embeded.enabled}</td>
                <td class="buttons">
                    <a class="button positive" href="users/${embeded.id}?form">Edit</a>
                    <button class="negative" type="button" onclick="deleteElement('${listUrl}',${embeded.id})">Remove</button>
                </td>
            </tr>
        </c:forEach></tbody>
    </c:if>
</table>

<div class="buttons">
    <a class="button positive" href="${addUrl}">Add</a>
</div>
