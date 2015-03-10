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
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Schools</title>
</head>
<body>

<%--<a href="<c:url value="/index" />">schools</a>--%>
<%--<br/>--%>
<%--<a href="<c:url value="/schools/getall" />">Schools RESTful</a>--%>
<%--<br/>--%>
<table border="1">
    <tr>
        <th>Login</th>
        <th>Password</th>
        <th>Roles</th>
        <th>Вescription</th>
    </tr>
    <tr>
        <td>admin</td>
        <td>pass</td>
        <td>ROLE_ADMIN,ROLE_USER</td>
        <td></td>
    <%--<td>This user able to do everything he want!</td>--%>
    </tr>
    <tr>
        <td>user1</td>
        <td>1111</td>
        <td>ROLE_USER</td>
        <td></td>
    </tr>
    <tr>
        <td>user2</td>
        <td>2222</td>
        <td>ROLE_USER</td>
        <td></td>
    </tr>
    
    <user name="admin" password="pass" authorities="ROLE_ADMIN,ROLE_USER" />
    <user name="user1" password="1111" authorities="ROLE_USER" />
    <user name="user2" password="2222" disabled="true" authorities="ROLE_USER" />
</table>

<c:if test="${not empty param.error}">
    <font color="red"> Login Error: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
</c:if>
<form method="POST" action="/tok">
    <table>
        <tr>
            <td align="right">Login</td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td align="right">Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
        <tr><input type="hidden" name="redirect_uri" value="<%= request.getParameter("redirect_uri") %>"></tr>
        <%--<tr>--%>
            <%--<td align="right">remember</td>--%>
            <%--<td><input type="checkbox" name="_spring_security_remember_me" /></td>--%>
        <%--</tr>--%>
        <tr>
            <td colspan="2" align="right"><input type="submit" value="Login" />
                <input type="reset" value="Reset" /></td>
        </tr>
    </table>
</form>
</body>
</html>
