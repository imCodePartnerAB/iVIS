<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set value="/role" var="mainPath"/>
<spring:url value="${mainPath}" var="mainUrl"/>
<spring:url value="${mainPath}" var="backUrl"/>
<spring:url value="/resources/js/jquery.tristate.js" var="JQueryTristateUrl"/>
<h1>Edit Role</h1>
<form:form modelAttribute="entity" id="roleUpdateForm" method="post">
    <div class="field">
        <form:label path="name">
            Name*
        </form:label>
        <form:input path="name" cssErrorClass="error"/>
        <form:errors path="name" cssClass="error-description"/>
    </div>
    <div class="field">
        <form:label path="userRole">
            For*
        </form:label>
        <form:select path="userRole" cssErrorClass="error">
            <form:option value="true" label="USERS"/>
            <form:option value="false" label="CLIENTS"/>
        </form:select>
        <form:errors path="userRole" cssClass="error-description"/>
    </div>

    <c:set var="prevEntityName" value=""/>
    <c:set var="divClosed" value="true"/>
    <c:forEach items="${permissions}" var="permission">

        <c:if test="${prevEntityName != permission.entityName && !divClosed}">
            </div>
            <c:set var="divClosed" value="true"/>
        </c:if>

        <c:if test="${prevEntityName != permission.entityName && divClosed}">
            <div class="field">
                <label for="${permission.entityName}">
                    <span class="arrow" onclick="showOrHideElementByLabel(this);"></span>${permission.entityName}
                    <input type="checkbox" class="tristate" for="${permission.entityName}"/>
                </label>
            </div>
            <div id="${permission.entityName}" class="non-display indent">
            <c:set var="prevEntityName" value="${permission.entityName}"/>
            <c:set var="divClosed" value="false"/>
        </c:if>

                <div class="field">
                    <label for="permission${permission.id}">
                        <span class="arrow" onclick="showOrHideElementByLabel(this);"></span>${permission.methodName}
                        <input type="checkbox" onclick="permissionOnClick(this);" name="permissions" value="${permission.id}"
                               <c:if test="dd">checked</c:if>
                        />
                    </label>
                </div>
                <div id="permission${permission.id}" class="non-display indent">
                    <div class="field">
                        <label>Url: ${permission.url}</label>
                    </div>
                    <div class="field">
                        <label>Http method: ${permission.httpMethod}</label>
                    </div>
                    <div class="field">
                        <label>Return: ${permission.returnValue}</label>
                    </div>
                    <c:if test="${permission.parameters != null}">
                        <div class="field">
                            <label>Parameters:</label>
                        </div>
                        <div class="indent">
                            <c:set var="params" value="${fn:split(permission.parameters, ';')}" />
                            <c:forEach items="${params}" var="param">
                                <div class="field">
                                    <label>${param}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                </div>

        <c:set var="prevEntityName" value="${permission.entityName}"/>

    </c:forEach>

</form:form>

<script src="${JQueryTristateUrl}"></script>

<script>
    $(document).ready(function () {

        var $tristate = $('.tristate');
        $tristate.tristate({
            checked:            "Checked all",
            unchecked:          "Unchecked all",
            indeterminate:      "Some checked",

            change:             tristateOnChange

        });

    });
</script>