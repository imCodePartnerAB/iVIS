<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/resources/js/registration_validation.js" var="jQueryValidationUrl"/>
<spring:url value="/registration/do" var="registrationUrl"/>
<head>
    <script src="${jQueryValidationUrl}"></script>
</head>

<div class="registration-form">

    <h1>iVIS Registration Page</h1>

    <form:form action="${registrationUrl}" commandName="user" id="registrationForm" method="post">

        <div class="field">
            <form:label path="username">
                Username*
            </form:label>
            <form:input path="username" id="username" cssErrorClass="error"/>
            <form:errors path="username" cssClass="error-description"/>
        </div>

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

        <div class="field">
            <label for="firstName">First name*</label>
            <input id="firstName" name="firstName"/>
            <%--<form:label path="person.firstName">--%>
                <%--First name*--%>
            <%--</form:label>--%>
            <%--<form:input path="person.firstName" id="person.firstName" cssErrorClass="error"/>--%>
            <%--<form:errors path="person.firstName" cssClass="error-description"/>--%>
        </div>

        <div class="field">
            <label for="lastName">Last name*</label>
            <input id="lastName" name="lastName"/>
            <%--<form:label path="person.lastName">--%>
                <%--Last name*--%>
            <%--</form:label>--%>
            <%--<form:input path="person.lastName" id="person.firstName" cssErrorClass="error"/>--%>
            <%--<form:errors path="person.lastName" cssClass="error-description"/>--%>
        </div>

        <div class="field">
            <label for="email">Email*</label>
            <input id="email" name="email"/>
        </div>

        <div class="field">
            <label for="contactPhone">Contact phone*</label>
            <input id="contactPhone" name="contactPhone"/>
        </div>

        <div class="buttons">
            <button type="submit" class="positive" name="registration" value="Register">Register</button>
        </div>
    </form:form>
</div>



