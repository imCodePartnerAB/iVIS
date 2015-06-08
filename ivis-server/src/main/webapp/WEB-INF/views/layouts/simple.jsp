<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="" prefix=""%>--%>
<spring:message var="labelApplicationName" code="label.application.name"/>
<spring:theme code="styleSheet" var="app_css"/>
<spring:url value="/${app_css}" var="app_css_url"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=8"/>
        <link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}"/>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
        <title>${labelApplicationName}</title>
    </head>
    <body>
        <div class="content">
            <div class="box">
                <tiles:insertAttribute name="content"/>
            </div>
        </div>
    </body>
</html>
