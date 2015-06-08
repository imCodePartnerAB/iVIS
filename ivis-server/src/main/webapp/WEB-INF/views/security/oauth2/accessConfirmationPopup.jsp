<%@ page import="org.springframework.security.core.AuthenticationException" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null && !(session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) instanceof UnapprovedClientAuthenticationException)) { %>
<div class="error">
    <h2>Woops!</h2>

    <p>Access could not be granted.
        (<%= ((AuthenticationException) session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY)).getMessage() %>
        )</p>
</div>
<% } %>

<c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>

<sec:authorize ifAllGranted="ROLE_USER">
    <div class="authorize-pop-up-form">
        <h1>Access Confirmation</h1>

        <div class="notification">
            Are you sure to confirm access for application "<c:out value="${client.name}"/>" to your account data?
        </div>
        <form method="post" action="<%=request.getContextPath()%>/oauth/authorize">
            <input name="user_oauth_approval" value="true" type="hidden"/>
            <c:forEach items="${scopes}" var="scope">
                <c:set var="approved">
                    <c:if test="${scope.value}"> checked</c:if>
                </c:set>
                <c:set var="denied">
                    <c:if test="${!scope.value}"> checked</c:if>
                </c:set>
                <div class="radio">
                    <label class="name">${scope.key}</label>
                    <input id="${scope.key}Approve" name="${scope.key}" type="radio" value="true" ${approved} />
                    <label for="${scope.key}Approve">Approve</label>
                    <input id="${scope.key}Deny" name="${scope.key}" type="radio" value="true" ${denied}/>
                    <label for="${scope.key}Deny">Deny</label>
                </div>
            </c:forEach>
            <div class="buttons">
                <button class="positive" type="submit">Confirm access</button>
            </div>
        </form>
    </div>
</sec:authorize>