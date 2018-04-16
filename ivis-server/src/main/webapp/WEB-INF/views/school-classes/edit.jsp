<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h1>Edit School Class</h1>
<form:form modelAttribute="entity" method="post">

    <div class="checkbox">
        <form:checkbox path="nextCloudEnabled" value="true" cssErrorClass="error" label="Next Cloud Enabled"/>
        <form:errors path="nextCloudEnabled" cssClass="error-description"/>
    </div>

    <form:hidden path="name"/>

    <div class="buttons">
        <button class="positive" type="submit">Save</button>
        <a class="button neutral" href="${backUrl}">Back</a>
    </div>

</form:form>