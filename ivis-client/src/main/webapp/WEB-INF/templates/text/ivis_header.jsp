<%@ page import="imcode.server.Imcms,
                 imcode.services.utils.IvisOAuth2Utils" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<imcms:variables/>
<%

    //String documentationUrl = "http://doc.imcms.net/SNAPSHOT";
//
//Perl5Util re = new Perl5Util();
//
//if (re.match("/^(.*\\/)(\\d)(\\.\\d).*/", documentationUrl)) {
//try {
//int majorVersion = Integer.parseInt(re.group(2));
//if (majorVersion > 5) {
//majorVersion = 5;
//}
//documentationUrl = re.group(1) + majorVersion + re.group(3);
//} catch (Exception ignore) {
//}
//}
//
//request.setAttribute("documentationUrl", documentationUrl);

    ApplicationContext context = IvisOAuth2Utils.getSpringContext(request);
    AuthorizationCodeResourceDetails client = context.getBean(AuthorizationCodeResourceDetails.class);
    boolean isAuthorized = IvisOAuth2Utils.isTokenGood(request);
    request.setAttribute("isAuthorized", isAuthorized);
    request.setAttribute("authUrl", IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri")));
    request.setAttribute("clientAddress", Imcms.getServerProperties().getProperty("ClientAddress"));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

    <title><c:out value="${document.headline}"/> - Powered by imCMS from imCode Partner AB</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/general.css"/>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>--%>
    <%--<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.js"></script>--%>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.1.0.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/jquery.validate.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.1/additional-methods.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/general.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/jquery.cookie.js"></script>
</head>
<body>

<div class="container">
    <div class="side">
        <div class="box">
            <div class="title">
                iVIS
            </div>
            <div class="main-menu">
                <a class="main-menu-item" href="${clientAddress}">Ansökningar</a>
                <%--<a class="main-menu-item" href="${clientAddress}/pupils">Elever</a>--%>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="box">
            <c:if test="${not isAuthorized}">
            <h1>Authorizing…</h1>
            <script type="text/javascript">
                ivisOAuth("${authUrl}");
            </script>
            </c:if>