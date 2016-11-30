<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 05.03.15
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--%>
    <%--<meta http-equiv="X-UA-Compatible" content="IE=8" />--%>
    <%--&lt;%&ndash;<link href="/resources/styles/standard.css" media="screen" type="text/css" rel="stylesheet">&ndash;%&gt;--%>
    <%--<spring:theme code="styleSheet" var="app_css" />--%>
    <%--<spring:url value="/${app_css}" var="app_css_url" />--%>
    <%--<link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}" />--%>
    <%--<title>iVIS Login Page</title>--%>
    <%--&lt;%&ndash;<spring:theme code="styleSheet" var="app_css" />&ndash;%&gt;--%>
    <%--&lt;%&ndash;<spring:url value="/${app_css}" var="app_css_url" />&ndash;%&gt;--%>
    <%--&lt;%&ndash;<link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}" />&ndash;%&gt;--%>
    <%--&lt;%&ndash;<link href="/resources/_styles/general.css" rel="stylesheet">&ndash;%&gt;--%>
    <%--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">--%>
<%--</head>--%>
<%--<body>--%>
<c:if test="${not empty param.error}">
    <font color="red"> Login Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<div class="login-form">
    <h1>iVIS Login Page</h1>
    <form action="<c:url value="/login.do"/>" method="post">
        <div class="field">
            <label>Login</label>
            <input type="text" name="j_username"/>
        </div>
        <div class="field">
            <label>Password</label>
            <input type="password" name="j_password"/>
        </div>
        <div class="buttons">
            <button type="submit" name="login" value="Login" >Login</button>
        </div>
    </form>
</div>
<%--</html>--%>
