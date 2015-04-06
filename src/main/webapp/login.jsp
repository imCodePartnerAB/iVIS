<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 05.03.15
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <title>iVIS Login Page</title>
    <link href="/resources/_styles/general.css" rel="stylesheet">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
</head>
<body>
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
</html>
