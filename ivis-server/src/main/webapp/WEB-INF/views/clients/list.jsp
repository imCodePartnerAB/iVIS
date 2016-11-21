<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.client.list" var="labelClientList"/>
<spring:url value="/clients/?form" var="addUrl"/>
<h1>${labelClientList}</h1>
<table cellpadding="0" cellspacing="0" >
    <thead>
    <tr>
        <th>Id</th>
        <th class="ordered-by">Name</th>
        <th>Resource Ids</th>
        <th>Roles</th>
        <th>&nbsp;</th>
    </tr>
    </thead>
    <c:if test="${not empty clients}">
        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.clientId}</td>
                <td>${client.name}</td>
                <td>${client.resourceIds}</td>
                <td>
                    <c:forEach items="${client.authorities}" var="role" varStatus="fileOptionStatus">
                        <c:if test="${!role.internal}">
                            ${role.name}
                            <c:if test="${!fileOptionStatus.last}">, </c:if>
                        </c:if>
                    </c:forEach>
                </td>
                <td class="buttons">
                    <a class="button positive" href="clients/${client.clientId}?form">Edit</a>
                    <button class="negative" type="button" onclick="location.href = 'clients/${client.clientId}?delete';">
                        Remove
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </c:if>
</table>

<div class="buttons">
    <a class="button positive" href="${addUrl}">Add</a>
</div>
