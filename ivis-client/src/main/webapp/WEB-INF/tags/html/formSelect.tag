<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="items" required="true" type="java.lang.Iterable" rtexprvalue="true" %>
<%@ attribute name="path" required="true" rtexprvalue="true" %>
<%@ attribute name="label" required="false" rtexprvalue="true" %>
<div class="field" id="${path}Select">
  <c:if test="${not empty label}">
    <form:label path="${path}">${label}</form:label>
  </c:if>
  <form:select path="${path}" items="${items}" itemLabel="name" itemValue="id"/>
  <form:errors path="${path}" cssClass="error-description"/>
</div>