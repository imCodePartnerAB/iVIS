<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.client.list" var="labelClientList"/>
<spring:url value="/clients/?form" var="addClientUrl"/>
<h1>${labelClientList}</h1>
<table cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th>Id</th>
        <th class="ordered-by">Name</th>
        <th>Resource Ids</th>
        <%--<th>Secret</th>--%>
        <th>Scope</th>
        <%--<th>Authorized Grant Types</th>--%>
        <%--<th>Registered Redirect Uri</th>--%>
        <%--<th>Authorities</th>--%>
        <%--<th>Access Token Validity(sec)</th>--%>
        <%--<th>Refresh Token Validity(sec)</th>--%>
        <%--<th>Auto Approve</th>--%>
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
                    <%--<td>${client.clientSecret}</td>--%>
                <td>${client.scope}</td>
                    <%--<td>${client.authorizedGrantTypes}</td>--%>
                    <%--<td>${client.registeredRedirectUri}</td>--%>
                    <%--<td>${client.authorities}</td>--%>
                <%--<td>${client.accessTokenValiditySeconds}</td>--%>
                <%--<td>${client.refreshTokenValiditySeconds}</td>--%>
                <%--<td>${client.isAutoApprove(client.scope)}</td>--%>
                <td class="buttons">
                    <a class="button positive" href="clients/${client.clientId}?form">Edit</a>
                    <button class="negative" type="button">Remove</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </c:if>
</table>

<div class="buttons">
    <button class="positive" type="submit">Add</button>
</div>
<%--<div class="buttons">--%>
<%--<a href="${addClientUrl}">Add</a>--%>
<%--</div>--%>
