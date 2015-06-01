<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url var="logoutUrl" value="/logout.do"/>
<spring:url var="loginUrl" value="/login"/>
<spring:url value="/clients" var="clientsUrl"/>
<spring:url value="/users" var="usersUrl"/>
<spring:url value="/sql" var="sqlUrl"/>
<spring:url value="/xml" var="xmlUrl"/>
<spring:message var="labelMainMenu" code="label.mainMenu.title"/>
<spring:message var="labelClients" code="label.mainMenu.clientList"/>
<spring:message var="labelUsers" code="label.mainMenu.userList"/>
<spring:message var="labelChangePassword" code="label.security.changePassword"/>
<spring:message var="labelLogout" code="label.security.logout"/>
<%--<spring:message var="" code="label."/>--%>

<div id="menu">
    <div class="title">${labelMainMenu}</div>
    <div class="main-menu">
        <a class="main-menu-item" href="${clientsUrl}">${labelClients}</a>
        <a class="main-menu-item" href="${usersUrl}">${labelUsers}</a>
        <%--<a class="main-menu-item" href="${sqlUrl}">SQL</a>--%>
        <a class="main-menu-item" href="${xmlUrl}">Application import</a>
    </div>
    <div class="user-ph">
        <div class="user">
            <div class="box">
                <div class="full-name">
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.username"/>
                    </sec:authorize>
                </div>
                <div class="user-menu">
                    <a class="user-menu-item" href="/changepassword">${labelChangePassword}</a>
                    <a class="user-menu-item" href="${logoutUrl}">${labelLogout}</a>
                </div>
            </div>
        </div>
    </div>
</div>