<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="itl" uri="http://iVIS.com" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<spring:message code="label.user.list" var="labelUserList"/>
<spring:url value="/users/?form" var="addUrl"/>
<spring:url value="/users" var="listUrl"/>
<tiles:importAttribute name="entityListTitle"/>
<tiles:importAttribute name="columnNames"/>
<itl:entityListTitle title="${entityListTitle}"/>
<itl:entityListTable entities="${entities}" columns="${columnNames}">
    <h2>asfasdf</h2>
</itl:entityListTable>

<%--<table cellpadding="0" cellspacing="0">--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th>Id</th>--%>
        <%--<th class="ordered-by">Name</th>--%>
        <%--&lt;%&ndash;<th>Roles</th>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<th>Enabled</th>&ndash;%&gt;--%>
        <%--<th>&nbsp;</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<c:if test="${not empty entityList}">--%>
        <%--<tbody><c:forEach items="${userList}" var="user">--%>
            <%--<tr data-object-id="${user.id}">--%>
                <%--<td>${user.id}</td>--%>
                <%--<td>${user.name}</td>--%>
                <%--<td>--%>
                    <%--<c:forEach items="${user.authorities}" var="role" varStatus="status">--%>
                        <%--${role.name}--%>
                        <%--<c:if test="${!status.last}">, </c:if>--%>
                    <%--</c:forEach>--%>
                <%--</td>--%>
                <%--<td>${user.enabled}</td>--%>
                <%--<td class="buttons">--%>
                    <%--<a class="button positive" href="users/${user.id}?form">Edit</a>--%>
                    <%--<button class="negative" type="button" onclick="deleteElement('${listUrl}',${user.id})">Remove</button>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</c:forEach></tbody>--%>
    <%--</c:if>--%>
<%--</table>--%>

<%--<div class="buttons">--%>
    <%--<a class="button positive" href="${addUrl}">Add</a>--%>
<%--</div>--%>
