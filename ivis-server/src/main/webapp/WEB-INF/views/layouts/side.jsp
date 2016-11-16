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
<spring:url value="/roles" var="rolesUrl"/>
<spring:url value="/schools" var="schoolsUrl"/>
<spring:url value="/pupils" var="pupilsUrl"/>
<spring:url value="/sql" var="sqlUrl"/>
<spring:url value="/xml" var="xmlUrl"/>
<spring:url value="/csv" var="csvUrl"/>
<spring:message var="labelMainMenu" code="label.mainMenu.title"/>
<spring:message var="labelClients" code="label.mainMenu.clientList"/>
<spring:message var="labelUsers" code="label.mainMenu.userList"/>
<spring:message var="labelRoles" code="label.mainMenu.roleList"/>
<spring:message var="labelSchools" code="label.mainMenu.schoolList"/>
<spring:message var="labelPupils" code="label.mainMenu.pupilList"/>
<spring:message var="labelCsvImport" code="label.mainMenu.csvImport"/>
<spring:message var="labelChangePassword" code="label.security.changePassword"/>
<spring:message var="labelLogout" code="label.security.logout"/>
<%--<spring:message var="" code="label."/>--%>

<div id="menu">
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <div class="title">${labelMainMenu}</div>
        <div class="main-menu">
            <a class="main-menu-item" href="${clientsUrl}">${labelClients}</a>
            <a class="main-menu-item" href="${usersUrl}">${labelUsers}</a>
            <a class="main-menu-item" href="${rolesUrl}">${labelRoles}</a>
            <a class="main-menu-item" href="${schoolsUrl}">${labelSchools}</a>
            <a class="main-menu-item" href="${pupilsUrl}">${labelPupils}</a>
            <a class="main-menu-item" href="${csvUrl}">${labelCsvImport}</a>
        </div>
    </sec:authorize>
    <div class="user-ph">
        <div class="user">
            <div class="box">
                <sec:authorize access="isAuthenticated()">
                    <div class="full-name">
                        <sec:authentication property="principal.username"/>
                    </div>
                    <div class="user-menu">
                        <a class="user-menu-item" href="/users/<sec:authentication property="principal.id"/>?form">${labelChangePassword}</a>
                        <a class="user-menu-item" href="${logoutUrl}">${labelLogout}</a>
                    </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>