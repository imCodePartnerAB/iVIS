<%--
  Created by IntelliJ IDEA.
  User: vitaly
  Date: 20.03.15
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title>Schools</title>
    <script type="text/javascript">
        function onButtonClick(id){
            $.ajax({
                url: "/ivis/oauth/tokens/" + id,
                type: "DELETE",
                success: function ()
                {
                    $("[data-token-id='"+ id +"']").remove();
                }
            });
            return false;
        }
    </script>
</head>
<body>

<h2>Tokens</h2>
<table class="data" border="1">
    <tr>
        <th>Value</th>
        <th>Expiraion date</th>
        <th>Expired</th>
        <th>Type</th>
        <th>Refresh token</th>
        <th>Scope</th>
        <th>Additional info</th>
        <th>&nbsp;</th>
    </tr>
    <c:if test="${!empty tokens}">
        <c:forEach items="${tokens}" var="token">
            <tr data-token-id="${token.value}">
                <td>${token.value}</td>
                <td>${token.expiration}</td>
                <td>${token.expired}</td>
                <td>${token.tokenType}</td>
                <td>${token.refreshToken.value}</td>
                <td>${token.scope}</td>
                <td>${token.additionalInformation}</td>
                <td>
                   <a href="#link" onclick="return onButtonClick('${token.value}');">delete</a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>