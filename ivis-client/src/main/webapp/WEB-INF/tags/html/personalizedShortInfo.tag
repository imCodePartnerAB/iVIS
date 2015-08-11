<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="value" required="true" type="com.imcode.entities.superclasses.AbstractIdEntity" rtexprvalue="true" %>
<%@ attribute name="path" required="true" rtexprvalue="true" %>
<%@ attribute name="editUrl" required="true" rtexprvalue="true" %>
<%@ attribute name="label" required="false" rtexprvalue="true" %>
<div id="${path}.personInfo">
  <c:if test="${not empty label}">
    <h2>${label}</h2>
  </c:if>
  <input id="${path}" type="hidden" name="${path}" value="${value.id}">
  <b>Personal Id</b> <a href="${editUrl}?id=${value.id}">${value.person.personalId}</a>
  <br>
  <b>First name</b> ${value.person.firstName}
  <br>
  <b>Last name</b> ${value.person.lastName}
</div>