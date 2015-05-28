<%@ page import="imcode.server.Imcms,
                 imcode.server.ImcmsConstants" pageEncoding="UTF-8" %>
<%@ page import="org.apache.oro.text.perl.Perl5Util" %>
<%@ page import="com.imcode.imcms.addon.ivisclient.utils.IvisOAuth2Utils" %>
<%@ page import="java.io.Writer" %>
<%@ page import="org.springframework.security.oauth2.common.OAuth2AccessToken" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<%--%>
    <%--//    HttpSession pageContext.getSe--%>
    <%--String IVIS_TOKEN = "IVIS_TOKEN";--%>
    <%--String token = (String) session.getAttribute(IVIS_TOKEN);--%>

    <%--if (token == null) {--%>
<%--//        response.sendRedirect("http://localhost:8080/ivis/oauth/authorize?client_id=2e9fa895-e577-4156-844a-2cbb1ebbe06d&redirect_uri=http://localhost:8080/client&response_type=code&scope=read&state=M7bQDn");--%>
        <%--response.sendRedirect("http://localhost:8081/tonr2");--%>
        <%--return;--%>
    <%--}--%>

<%--//    System.out.println("sdf");--%>
<%--%>--%>

<imcms:variables/>
<%

    // TODO: Add support for imCMS versions > 5

    String documentationUrl = "http://doc.imcms.net/SNAPSHOT";

    Perl5Util re = new Perl5Util();

    if (re.match("/^(.*\\/)(\\d)(\\.\\d).*/", documentationUrl)) {
        try {
            int majorVersion = Integer.parseInt(re.group(2));
            if (majorVersion > 5) {
                majorVersion = 5;
            }
            documentationUrl = re.group(1) + majorVersion + re.group(3);
        } catch (Exception ignore) {
        }
    }

    request.setAttribute("documentationUrl", documentationUrl);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

    <title><c:out value="${document.headline}"/> - Powered by imCMS from imCode Partner AB</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/imcms/css/imcms_demo.css.jsp"/>

</head>
<body style="margin:10px; background-color:#eee;">
<%--<script type="text/javascript">--%>
    <%--window.location.href = "http://localhost:8080/ivis/oauth/authorize?client_id=2e9fa895-e577-4156-844a-2cbb1ebbe06d&redirect_uri=http://localhost:8080/client&response_type=code&scope=read&state=M7bQDn";--%>
<%--</script>--%>
<%
////    HttpSession pageContext.getSe
    if (IvisOAuth2Utils.getAccessToken(request) == null) {

//    String IVIS_TOKEN = "IVIS_TOKEN";
//    String token = (String) session.getAttribute(IVIS_TOKEN);
//
//    if (token == null) {
%>
<%--//        response.sendRedirect("http://localhost:8080/ivis/oauth/authorize?client_id=2e9fa895-e577-4156-844a-2cbb1ebbe06d&redirect_uri=http://localhost:8080/client&response_type=code&scope=read&state=M7bQDn");--%>
<%--//        response.sendRedirect("http://localhost:8081/tonr2");--%>
<%--//        return;--%>
<%--http://localhost:8080/ivis/oauth/authorize?client_id=2e9fa895-e577-4156-844a-2cbb1ebbe06d&redirect_uri=http://localhost:8080/client/api/content/ivis/code&response_type=code&scope=read--%>
<script type="text/javascript">
    window.location.href = "http://localhost:8080/ivis/oauth/authorize?client_id=2e9fa895-e577-4156-844a-2cbb1ebbe06d&redirect_uri=http://localhost:8080/client/api/content/ivis/code&response_type=code&scope=read";
</script>
<%
    } else {
        OAuth2AccessToken accessToken = IvisOAuth2Utils.getAccessToken(session);
        out.println(accessToken);
    }

//        }
//
////    System.out.println("sdf");
%>
</body>
</html>