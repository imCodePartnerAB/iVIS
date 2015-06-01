<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ taglib prefix="spirng" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>function k(e) {
    if (e.ctrlKey && e.which == 13) document.sqlForm.submit();
}</script>
XML import:
<c:if test="${message != null}">
    <br>
    ${message.message}
</c:if>
<form method="post" name="xmlForm">
    <textarea cols="60" rows="8" name="body" onkeyup="k(event);">
        ${body}
    </textarea>
    <br>
    <button type="submit">Import</button>
    <button type="reset">Reset</button>
</form>
