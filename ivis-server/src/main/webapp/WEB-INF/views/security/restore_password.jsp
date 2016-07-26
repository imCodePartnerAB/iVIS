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

        <form id="restorePasswordFormEmail" action="/restore_password/email">
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

    <c:if test="${not empty permission}">

        <form id="restorePasswordFormDo" action="/restore_password/do">
            <div class="field">
                <label>
                    Password
                </label>
                <input id="password" name="password"/>
            </div>

            <div class="field">
                <label>
                    Confirm password
                </label>
                <input id="confirmPassword" name="confirmPassword"/>
            </div>

            <div class="buttons">
                <button type="submit" class="positive" name="changePassword">Change password</button>
            </div>
        </form>

    </c:if>

    <label>After submitting check email</label>

</div>