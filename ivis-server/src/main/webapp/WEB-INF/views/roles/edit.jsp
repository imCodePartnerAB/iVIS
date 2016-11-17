<%--<?xml version="1.0" encoding="UTF-8" standalone="no"?>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set value="/roles" var="mainPath"/>
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
    <c:set var="prevEntityName" value="init"/>
    <c:set var="divClosed" value="true"/>
    <c:forEach items="${permissionsAll}" var="permission">
        <c:if test="${prevEntityName != permission.entityName && !divClosed}">
            <%--permission group by entity name div closed--%>
            </div>
            <c:set var="divClosed" value="true"/>
        </c:if>
        <c:if test="${prevEntityName != permission.entityName && divClosed}">
            <div class="field">
                <label for="${permission.entityName}">
                    <span class="arrow" onclick="showOrHideElementByLabel(this);"></span>${permission.entityName}
                    <input type="checkbox" class="tristate" onclick="tristateOnClick(this);" for="${permission.entityName}"/>
                </label>
            </div>
            <%--permission group by entity name div open--%>
            <div id="${permission.entityName}" class="non-display indent">
            <c:set var="prevEntityName" value="${permission.entityName}"/>
            <c:set var="divClosed" value="false"/>
            <script>
                $(document).ready(function () {
                    var $permissionCheckbox = $('input[value=${permission.id}]');
                    $permissionCheckbox.click();
                    $permissionCheckbox.click();
                });
            </script>
        </c:if>
                <div class="field">
                    <label for="permission${permission.id}">
                        <span class="arrow" onclick="showOrHideElementByLabel(this);"></span>${permission.methodName}
                        <input type="checkbox" onclick="permissionOnClick(this);" name="permissions" value="${permission.id}"
                               ${entity.permissions.contains(permission) ? 'checked' : ''}
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
                        <label>Return: ${f:escapeXml(permission.returnValue)}</label>
                    </div>
                    <c:if test="${permission.parameters != null}">
                        <div class="field">
                            <label>Parameters:</label>
                        </div>
                        <div class="indent">
                            <div class="field">
                                <label>${f:escapeXml(permission.parameters)}</label>
                            </div>
                        </div>
                    </c:if>
                </div>
        <c:set var="prevEntityName" value="${permission.entityName}"/>
    </c:forEach>
    <%--permission group by entity name div closed--%>
    </div>
    <div class="buttons">
        <button class="positive" type="submit">Save</button>
        <a class="button neutral" href="${backUrl}">Back</a>
    </div>
</form:form>
<script src="${JQueryTristateUrl}"></script>
<script>
    $(document).ready(function () {
        var $tristate = $('.tristate');
        $tristate.tristate({
            checked:            "Checked all",
            unchecked:          "Unchecked all",
            indeterminate:      "Some checked"
        });
    });
</script>