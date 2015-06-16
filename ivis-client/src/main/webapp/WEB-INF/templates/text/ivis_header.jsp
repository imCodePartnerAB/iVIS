<%@ page import="imcode.server.Imcms,
                 imcode.services.utils.IvisOAuth2Utils" pageEncoding="UTF-8" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--<c:set var="clientAddress" value=/>--%>
<%--<c:url var="applicationList" value="${clientAddress}applications"/>--%>
<%--<c:url var="pupilList" value="${clientAddress}pupils"/>--%>
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
//    boolean isAuthorized = IvisOAuth2Utils.getAccessToken(request) != null;
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/scripts/general.js"></script>
    <%--<script type="text/javascript">--%>
    <%--var isAuthorize = ${isAuthorized};--%>
    <%--var wnd;--%>
    <%--function ivisOAuth(authUrl) {--%>
    <%--var width = 600;--%>
    <%--var height = 350;--%>
    <%--var left = (screen.width / 2) - (width / 2);--%>
    <%--var top = (screen.height / 2) - (height / 2);--%>
    <%--wnd = window.open(authUrl, "newwinow", "left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + ", menubar=0, status=0, resizable=0, scrollbars=0");--%>
    <%--}--%>
    <%--function authComplete() {--%>
    <%--wnd.close();--%>
    <%--location.reload();--%>
    <%--}--%>
    <%--</script>--%>
</head>
<body>

<div class="container">
    <div class="side">
        <div class="box">
            <div class="title">
                iVIS
            </div>
            <div class="main-menu">
                <%--<c:url var="applicationList" value=""/>--%>
                <%--<c:url var="pupilList" value="${clientAddress}pupils"/>--%>
                <a class="main-menu-item" href="${clientAddress}">Applications</a>
                <a class="main-menu-item" href="${clientAddress}/pupils">Pupils</a>
            </div>
        </div>
    </div>
    <div class="content">
        <div class="box">
            <%--<%--%>
            <%--if (!isAuthorized) {--%>
            <%--%>--%>
            <c:if test="${not isAuthorized}">
            <h1>Authorizing…</h1>
            <script type="text/javascript">
                ivisOAuth("${authUrl}");
            </script>
            </c:if>

            <%--<%}%>--%>
            <%--else {--%>
            <%--&lt;%&ndash;OAuth2ClientContext clientContext = IvisOAuth2Utils.getClientContext(session);&ndash;%&gt;--%>

            <%--&lt;%&ndash;if (client != null) {&ndash;%&gt;--%>
            <%--&lt;%&ndash;IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.ServiceAddressBuilder()&ndash;%&gt;--%>
            <%--&lt;%&ndash;.endPointUrl(Imcms.getServerProperties().getProperty("ServerAddress"))&ndash;%&gt;--%>
            <%--&lt;%&ndash;.responseType("json")&ndash;%&gt;--%>
            <%--&lt;%&ndash;.version("v1").build());&ndash;%&gt;--%>
            <%--&lt;%&ndash;DefaultIvisServiceFactory factory = ivis.getServiceFactory(client, clientContext);&ndash;%&gt;--%>
            <%--&lt;%&ndash;StatementService service = factory.getStatementService();&ndash;%&gt;--%>
            <%--&lt;%&ndash;List<Statement> statementList = null;&ndash;%&gt;--%>
            <%--&lt;%&ndash;try {&ndash;%&gt;--%>
            <%--&lt;%&ndash;statementList = service.findAll();&ndash;%&gt;--%>
            <%--&lt;%&ndash;} catch (UserRedirectRequiredException e) {&ndash;%&gt;--%>
            <%--&lt;%&ndash;IvisOAuth2Utils.setAccessToken(session, null);&ndash;%&gt;--%>
            <%--&lt;%&ndash;response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));&ndash;%&gt;--%>
            <%--&lt;%&ndash;return;&ndash;%&gt;--%>
            <%--&lt;%&ndash;}&ndash;%&gt;--%>
            <%--&lt;%&ndash;request.setAttribute("statements", statementList);&ndash;%&gt;--%>
            <%--&lt;%&ndash;}&ndash;%&gt;--%>
            <%--&lt;%&ndash;}&ndash;%&gt;--%>
            <%--&lt;%&ndash;%>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:if test="${not empty statements}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<h1>Applications</h1>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<table cellpadding="0" cellspacing="0">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<thead>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th class="ordered-by">Id</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th>SubmitDate</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th>ChangeDate</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th>Status</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<th>&nbsp;</th>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</thead>&ndash;%&gt;--%>

            <%--&lt;%&ndash;<tbody>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<c:forEach items="${statements}" var="app">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr data-application-id="${app.id}">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>${app.id}</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>${app.submitDate}</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>${app.changedDate}</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td>${app.status}</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<td class="buttons">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<a class="button positive"&ndash;%&gt;--%>
            <%--&lt;%&ndash;href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/details.jsp?id=${app.id}">Details</a>&ndash;%&gt;--%>

            <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
            <%--&lt;%&ndash;method="post">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<button class="positive" type="submit">Approve</button>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="hidden" name="status" value="approved"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"&ndash;%&gt;--%>
            <%--&lt;%&ndash;method="post">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<button class="negative" type="submit">Decline</button>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<input type="hidden" name="status" value="declined"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</form>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tbody>&ndash;%&gt;--%>

            <%--&lt;%&ndash;</table>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="buttons">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<a class="button positive"&ndash;%&gt;--%>
            <%--&lt;%&ndash;href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menu no='1' docId="1001" label='<br/><br/>Meny (punktlista)'>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<ul>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menuloop>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menuitem>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<li style="padding-bottom:5px; color:green;"><imcms:menuitemlink><c:out&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;value="${menuitem.document.headline}"/></imcms:menuitemlink>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menuloop>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menuitem>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<div style="padding-bottom:5px; color:green;">&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:menuitemlink><c:out&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;value="${menuitem.document.headline}"/></imcms:menuitemlink>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</div>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</imcms:menuitem>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</imcms:menuloop>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</li>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</imcms:menuitem>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</imcms:menuloop>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</ul>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</imcms:menu>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<imcms:admin/>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<div class="col-sm-6">&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<div class="mb-md">&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<button id="addToTable" class="btn btn-primary" onclick="addApplication()">Add <i class="fa fa-plus"></i></button>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</div>&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;</div>&ndash;%&gt;&ndash;%&gt;--%>

            <%--</body>--%>
<%--</html>--%>