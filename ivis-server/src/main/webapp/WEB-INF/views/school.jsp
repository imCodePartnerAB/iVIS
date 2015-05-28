<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 05.03.15
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf8">--%>
    <%--<title>Schools</title>--%>
<%--</head>--%>
<body>

<a href="<c:url value="/logout.do" />">Logout</a>
<br/>
<a href="<c:url value="/schools"/>">Schools RESTful</a>
<br/>


<h2>Schools</h2>

<form:form method="post" action="add" commandName="school">

    <table>
        <tr>
            <td><form:label path="name">name</form:label></td>
            <td><form:input path="name" /></td>
            <td colspan="2"><input type="submit" value="add" /></td>
        </tr>
        <%--<tr>--%>
            <%--<td><form:label path="lastname">--%>
                <%--<spring:message code="label.lastname" />--%>
            <%--</form:label></td>--%>
            <%--<td><form:input path="lastname" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="email">--%>
                <%--<spring:message code="label.email" />--%>
            <%--</form:label></td>--%>
            <%--<td><form:input path="email" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td><form:label path="telephone">--%>
                <%--<spring:message code="label.telephone" />--%>
            <%--</form:label></td>--%>
            <%--<td><form:input path="telephone" /></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2"><input type="submit"--%>
                                   <%--value="add" /></td>--%>
        <%--</tr>--%>
    </table>
</form:form>

<h3>schools</h3>
<c:if test="${!empty schoolList}">
    <table class="data">
        <tr>
            <th>id</th>
            <th>name</th>
            <%--&lt;%&ndash;<th><spring:message code="label.telephone" /></th>&ndash;%&gt;--%>
            <th>&nbsp;</th>
        </tr>
        <c:forEach items="${schoolList}" var="school">
            <tr>
                <td>${school.name}</td>
                <%--<td>${school.email}</td>--%>
                <%--<td>${school.telephone}</td>--%>
                <td><a href="delete/${school.id}">delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
<%--</html>--%>
