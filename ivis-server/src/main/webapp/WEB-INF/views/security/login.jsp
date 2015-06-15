<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<spring:url value="/registration" var="registrationUrl"/>
<spring:url value="/restore_password" var="restoreUrl"/>
<spring:url value="/login.do" var="loginUrl"/>
<spring:url value="/logout.do" var="logoutUrl"/>
<spring:message code="label.security.title" var="labelTitle"/>
<spring:message code="label.security.username" var="labelUsername"/>
<spring:message code="label.security.password" var="labelPassword"/>
<spring:message code="label.security.registration" var="labelRegistration"/>
<spring:message code="label.security.registrationDesc" var="labelRegistrationDesc"/>
<spring:message code="label.security.restorePassword" var="labelRestorePassword"/>
<spring:message code="label.security.restorePasswordDesc" var="labelRestorePasswordDesc"/>
<c:if test="${not empty param.error}">
    <font color="red"> Login Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<div class="login-form">
    <h1>${labelTitle}</h1>

    <form action="${loginUrl}" method="post">
        <div class="field">
            <label>${labelUsername}</label>
            <input type="text" name="j_username"/>
        </div>
        <div class="field">
            <label>${labelPassword}</label>
            <input type="password" name="j_password"/>
        </div>
        <div class="buttons">
            <button type="submit" class="positive" name="login" value="Login">Login</button>
        </div>
        <h2>${labelRegistrationDesc}</h2>
        <a href="${registrationUrl}">${labelRegistration}</a>
        <h2>${labelRestorePasswordDesc}</h2>
        <a href="${restoreUrl}">${labelRestorePassword}</a>
    </form>
</div>
