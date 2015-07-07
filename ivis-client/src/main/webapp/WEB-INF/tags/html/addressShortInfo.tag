<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="value" required="true" type="com.imcode.entities.embed.Address" rtexprvalue="true" %>
<%--<%@ attribute name="path" required="true" rtexprvalue="true" %>--%>
<%--<%@ attribute name="editUrl" required="true" rtexprvalue="true" %>--%>
<%@ attribute name="label" required="false" rtexprvalue="true" %>
<div id="addressInfo">
  <c:if test="${not empty label}">
    <h2>${label}</h2>
  </c:if>
  <%--<input id="${path}" type="hidden" name="${path}.id" value="${value.id}">--%>
  <b>Type of address</b> ${value.addressType}<br>
  <b>Street name</b> ${value.street}<br>
  <b>Postal code</b> ${value.postalCode}<br>
  <b>City</b> ${value.city}
</div>