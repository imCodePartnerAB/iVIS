<%@ page import="com.imcode.entities.Statement,
                 com.imcode.services.StatementService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.restful.IvisFacade" %>
<%@ page import="imcode.services.restful.DefaultIvisServiceFactory" %>
<%@ page import="imcode.services.utils.IvisOAuth2Utils" %>
<%@ page import="org.apache.oro.text.perl.Perl5Util" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails" %>
<%@ page import="org.springframework.security.oauth2.common.OAuth2AccessToken" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.security.oauth2.client.resource.UserRedirectRequiredException" %>
<%@ page import="org.springframework.security.oauth2.client.OAuth2ClientContext" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

</div>
</div>
</div>
</body>
</html>