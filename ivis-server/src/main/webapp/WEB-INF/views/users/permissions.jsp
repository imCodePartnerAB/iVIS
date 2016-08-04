<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<spring:url value="/users" var="backUrl"/>

<h1>Permissions of user</h1>

<form action="/users/permit">

<form:form modelAttribute="user" id="userPermissionsForm" method="post">




    <form:checkboxes path="allowedMethods" items="${methodRestProviderForEntityList}" itemLabel="name" cssErrorClass="error"
                     cssClass="check-box" itemValue="id"/>


</form:form>


</form>


