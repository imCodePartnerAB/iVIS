<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Error</h1>
<div class="field">
    <label>Error code: ${errorCode}</label>
    <label>Error message: ${errorMsg}</label>
    <label>Description:</label>
    <c:forEach items="${errorDescription}" var="description" >
        <label>${description}</label>
    </c:forEach>
</div>
