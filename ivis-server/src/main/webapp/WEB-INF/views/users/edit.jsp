<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/users" var="backUrl"/>

<h1>Edit User</h1>
    <%--<c:if test="${not empty message}">--%>
    <%--<div id="message" class="${message.type}">${message.message}</div>--%>
    <%--</c:if>--%>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
        <form:form modelAttribute="user" id="userUpdateForm" method="post">
            <div class="checkbox">
                <form:checkbox path="enabled" value="true" cssErrorClass="error" label="Enabled"/>
                <form:errors path="enabled" cssClass="error-description"/>
            </div>

            <div class="field">
                <form:label path="username">
                    Name*
                </form:label>
                <form:input path="username" cssErrorClass="error"/>
                <form:errors path="username" cssClass="error-description"/>
            </div>
            <div class="field">
                <form:label path="password">
                    Password*
                </form:label>
                <label
                        onclick="$(this.parentElement).hide();"
                        style="color: blue;">Don't change</label>
                <form:input path="password" id="password" cssErrorClass="error"/>
                <form:errors path="password" cssClass="error-description"/>
            </div>
            <div class="checkbox">
                <form:checkbox path="clientOwner" cssErrorClass="error" label="Client owner"/>
                <form:errors path="clientOwner" cssClass="error-description"/>
            </div>
            <div class="checkbox">
                <form:label path="authorities">
                    Roles*
                </form:label>
                <form:checkboxes path="authorities" items="${roleList}" itemLabel="authority" cssErrorClass="error"
                                 cssClass="check-box" itemValue="id"/>
                <form:errors path="authorities" cssClass="error-description"/>
            </div>
            <div class="buttons">
                <button class="positive" type="submit">Save</button>
                <a class="button neutral" href="${backUrl}">Back</a>
            </div>
            <%--<div class="field">--%>
                <%--<form:label path="confirmPassword">--%>
                    <%--Confirm password*--%>
                <%--</form:label>--%>
                <form:hidden path="confirmPassword" id="confirmPassword" cssErrorClass="error"/>
                <form:errors path="confirmPassword" cssClass="error-description"/>
            <%--</div>--%>
        </form:form>
    </sec:authorize>

    <sec:authorize access="!hasRole('ROLE_ADMIN')">
        <form id="userChangePasswordForm" action="/users/${user.id}?passwordchange" method="post">
            <div class="field">
                <label>
                    Old password*
                </label>
                <input id="oldPassword" name="oldPassword" type="password"/>
            </div>

            <div class="field">
                <label>
                    Password*
                </label>
                <input id="password" name="password" type="password"/>
            </div>

            <div class="field">
                <label>
                    Confirm password*
                </label>
                <input id="confirmPassword" name="confirmPassword" type="password"/>
            </div>

            <div class="buttons">
                <button class="positive" type="submit">Save</button>
            </div>

            Check email for details
        </form>
    </sec:authorize>


