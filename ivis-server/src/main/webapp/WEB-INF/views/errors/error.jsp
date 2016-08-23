<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Error</h1>
<div class="field">
    <label>Error code: ${generalError.errorCode}</label>
    <label>Error message: ${generalError.errorMessage}</label>
    <label>Description:</label>
    <c:forEach items="${generalError.errorDescription}" var="description" >
        <label>${description}</label>
    </c:forEach>
</div>
