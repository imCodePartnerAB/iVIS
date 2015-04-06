<%@ taglib prefix="spirng" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 06.04.15
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<c:if test="${not empty message}">
    <font color="#b22222">${message}</font>
    <br>
</c:if>
SQL Executor:
<form action="/sql" method="post">
    <textarea cols="60" rows="8" name="sql">
SELECT * FROM db_ivis.oauth_client_details;
    </textarea>
    <br>
    <button type="submit" name="action" value="select">Select</button>
    <button type="submit" name="action" value="update">Update</button>
    <button type="reset">Reset</button>
</form>
<c:if test="${not empty results}">
    <%--<table border="1">--%>
        <%--<thead>--%>
            <%--<tr>--%>

            <%--</tr>--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--<c:forEach var="resultMap" items="results">--%>
            <%--<tr>--%>
                <%--<c:forEach var="resulRow" items="resultMap">--%>
                    <%--<td>${resulRow}</td>--%>
                <%--</c:forEach>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
        <%--</tbody>--%>
    <%--</table>--%>
<h1>Selected rows: ${results.size()}</h1>
</c:if>
<c:if test="${not empty updateRows}">
    <h1>Updated rows: ${updateRows}</h1>
</c:if>
</body>
</html>
