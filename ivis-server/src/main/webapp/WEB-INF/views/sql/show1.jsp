<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
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
    <title>Sql Executor</title>
    <script>function k(e) {
        if (e.ctrlKey && e.which == 13) document.sqlForm.submit();
    }</script>
</head>
<body>
SQL Executor:
<form method="post" name="sqlForm">
    <textarea cols="60" rows="8" name="sql" onkeyup="k(event);">
        ${sqlString}
    </textarea>
    <br>
    <button type="submit">Execute</button>
    <button type="reset">Reset</button>
</form>
<c:if test="${not empty resultList and resultList.size() gt 0}">
    <%
        List<Map<String, Object>> results = (List<Map<String, Object>>) request.getAttribute("resultList");
        int i = 0;
        for (Map<String, Object> resultMap : results) {
            Integer rowCount = (Integer) resultMap.get("rowCount");
            List<Map<String, Map<String, String>>> result = (List<Map<String, Map<String, String>>>) resultMap.get("result");
            String errorMessage = (String) resultMap.get("errorMessage");

            out.println("<h2>Sql: ");
            out.print(i++);

            if (rowCount != null) {
                out.print(" (" + rowCount + ")");
            }

            out.println("</h2>");
            out.println("<br>");

            if (errorMessage != null) {
                out.println("<font color=\"#b22222\">" + errorMessage + "</font>");
                out.println("<br>");
            }

            if (result != null && result.size() > 0) {%>
    <table border="1">
        <thead>
        <tr bgcolor="#a9a9a9">
            <%
                Set<String> headers = result.get(0).keySet();
                for (String header : headers) {
                    out.println("<td>" + header + "</td>");
                }
            %>
        </tr>
        </thead>
        <tbody>
        <%
            for (Map<String, Map<String, String>> row : result) {
                out.println("<tr>");
                for (Map.Entry<String, Map<String, String>> stringMapEntry : row.entrySet()) {
                    out.println("<td>");
                    out.println(stringMapEntry.getValue());
                    out.println("</td>");
                }
                out.println("</tr>");
            }

        %>
        </tbody>
    </table>
    <%
            }

        }

    %>
</c:if>
</body>
</html>
