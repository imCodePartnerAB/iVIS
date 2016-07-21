<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="" prefix=""%>--%>
<spring:theme code="styleSheet" var="app_css"/>
<spring:url value="/${app_css}" var="app_css_url"/>
<spring:url value="/resources/js/forms.js" var="jsFormsUrl"/>
<spring:url value="/resources/js/validation.js" var="jQueryValidationUrl"/>
<%--<spring:url value="/resources/js/jquery-1.7.1.js" var="jqueryUrl"/>--%>
<spring:message var="labelApplicationName" code="label.application.name"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}"/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${jsFormsUrl}"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
    <script type="text/javascript" src="${jQueryValidationUrl}"></script>
    <title>${labelApplicationName}</title>
</head>
<body>
<div class="container">
    <div class="side">
        <div class="box">
            <tiles:insertAttribute name="side" ignore="true"/>
        </div>
    </div>
    <div class="content">
        <div class="box">
            <tiles:insertAttribute name="content"/>
        </div>
    </div>
</div>
</body>
</html>