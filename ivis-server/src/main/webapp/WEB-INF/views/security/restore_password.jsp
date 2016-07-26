<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/resources/js/restore_password_validation.js" var="jQueryValidationUrl"/>
<spring:url value="/registration/do" var="registrationUrl"/>
<head>
    <script src="${jQueryValidationUrl}"></script>
</head>

<div class="restore-password-form">

    <h1>iVIS Restore Password Page</h1>
    
    <c:if test="${empty permission}">

        <form id="restorePasswordFormEmail" action="/restore_password/email" method="post">
            <div class="field">
                <label>
                    Enter email
                </label>
                <input id="email" name="email"/>
            </div>

            <div class="buttons">
                <button type="submit" class="positive" name="approve">Approve</button>
            </div>
        </form>

    </c:if>

    <c:if test="${permission.equals('allow')}">

        <form:form action="/restore_password/do" commandName="user" id="restorePasswordFormDo" method="post">
            <div class="field">
                <form:label path="password">
                    Password*
                </form:label>
                <form:password path="password" id="password" cssErrorClass="error"/>
                <form:errors path="password" cssClass="error-description"/>
            </div>

            <div class="field">
                <form:label path="confirmPassword">
                    Confirm password*
                </form:label>
                <form:password path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>
                <form:errors path="confirmPassword" cssClass="error-description"/>
            </div>

            <div class="buttons">
                <button type="submit" class="positive" name="changePassword">Change password</button>
            </div>
        </form:form>

    </c:if>

    <label>After submitting check email</label>

</div>