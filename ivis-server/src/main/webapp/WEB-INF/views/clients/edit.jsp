<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/clients" var="backUrl"/>
<h1>Edit Client</h1>


<form:form modelAttribute="client" id="clientUpdateForm" method="post">
    <div class="field">
        <form:label path="name">
            Name*
        </form:label>
        <form:input path="name" cssErrorClass="error"/>
        <form:errors path="name" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="resourceIds">
            Resources*
        </form:label>
        <form:input path="resourceIds"/>
        <form:errors path="resourceIds" cssClass="error"/>
    </div>
    <div class="field">
        <form:label path="owner">
            Owner*
        </form:label>
        <form:select path="owner" cssErrorClass="error" items="${userList}" itemValue="id" itemLabel="name"/>
        <form:errors path="owner" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="clientSecret">
            Secret*
        </form:label>
        <form:input path="clientSecret" cssErrorClass="error"/>
        <form:errors path="clientSecret" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="authorizedGrantTypes">
            Authorized Grant Types*
        </form:label>
        <form:select path="authorizedGrantTypes" items="${grantTypes}" multiple="true" cssErrorClass="error">
        </form:select>
        <form:errors path="authorizedGrantTypes" cssClass="error-description"/>
    </div>
    <div class="field">
    <form:label path="registeredRedirectUri">
    <%--${client.registeredRedirectUri}--%>
        Registered Redirect Uri*
    </form:label>
    <form:input path="registeredRedirectUri" cssErrorClass="error"/>
        <form:errors path="registeredRedirectUri" cssClass="error-description"/>
    </div>
    <div class="checkbox">
        <form:label path="roles">
            Roles*
        </form:label>
        <form:checkboxes path="roles" items="${roleList}" itemLabel="name" cssErrorClass="error"
                         cssClass="check-box" itemValue="id" />
        <form:errors path="roles" cssClass="error-description"/>
    </div>
    <div class="field">
    <form:label path="accessTokenValiditySeconds">
    Access Token Validity(sec)*
    </form:label>
    <form:input path="accessTokenValiditySeconds" id="accessTokenValiditySeconds"/>
    <div>
    <form:errors path="accessTokenValiditySeconds" cssClass="error"/>
    </div>
    <div class="field">
    <form:label path="refreshTokenValiditySeconds">
    Refresh Token Validity(sec)*
    </form:label>
    <form:input path="refreshTokenValiditySeconds" id="refreshTokenValiditySeconds"/>
    <div>
    <form:errors path="refreshTokenValiditySeconds" cssClass="error"/>
    </div>

    <div class="buttons">
        <button class="positive" type="submit">Save</button>
        <a class="button neutral" href="${backUrl}">Back</a>
    </div>
</form:form>
