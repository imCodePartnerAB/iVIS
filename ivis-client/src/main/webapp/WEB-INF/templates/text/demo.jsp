<%@ page import="com.imcode.entities.Statement,
                 com.imcode.services.StatementService" pageEncoding="UTF-8" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.services.restful.IvisFacade" %>
<%@ page import="imcode.services.restful.IvisServiceFactory" %>
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
<imcms:variables/>
<%

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

    ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
    AuthorizationCodeResourceDetails client = context.getBean(AuthorizationCodeResourceDetails.class);
    boolean isAuthorized = IvisOAuth2Utils.getAccessToken(request) != null;
    request.setAttribute("isAuthorized", isAuthorized);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

    <title><c:out value="${document.headline}"/> - Powered by imCMS from imCode Partner AB</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" rel="stylesheet" type="text/css">
    <%--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/imcms/css/imcms_demo.css.jsp"/>--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/general.css"/>
    <script type="text/javascript">
        var isAuthorize = ${isAuthorized};
        var wnd;
        function ivisOAuth() {
            var authUrl = "<%=IvisOAuth2Utils.getOAuth2AuthirizationUrl(client, Imcms.getServerProperties().getProperty("AuthorizationCodeHandlerUri"))%>";
            var width = 600;
            var height = 350;
            var left = (screen.width / 2) - (width / 2);
            var top = (screen.height / 2) - (height / 2);
            wnd = window.open(authUrl, "newwinow", "left=" + left + ", top=" + top + ", width=" + width + ", height=" + height + ", menubar=0, status=0, resizable=0, scrollbars=0");
        }
        function authComplete() {
            wnd.close();
            location.reload();
        }
    </script>
</head>
<body>
<div class="container">
    <div class="content">
        <div class="box">
<%
    if (!isAuthorized) {
%>
            <c:if test="${not isAuthorized}">
                <h1>Authorizing…</h1>
                <script type="text/javascript">
                    ivisOAuth();
                </script>
            </c:if>

<%
    } else {
        OAuth2ClientContext clientContext = IvisOAuth2Utils.getClientContext(session);

        if (client != null) {
            IvisFacade ivis = IvisFacade.instance(new IvisFacade.Configuration.Builder()
                    .endPointUrl(Imcms.getServerProperties().getProperty("ServerAddress"))
                    .responseType("json")
                    .version("v1").build());
            IvisServiceFactory factory = ivis.getServiceFactory(client, clientContext);
            StatementService service = factory.getStatementService();
            List<Statement> statementList = null;
            try {
                statementList = service.findAll();
            } catch (UserRedirectRequiredException e) {
                IvisOAuth2Utils.setAccessToken(session, null);
                response.sendRedirect(Imcms.getServerProperties().getProperty("StatementsAddress"));
                return;
            }
            request.setAttribute("statements", statementList);
        }
    }
%>
            <c:if test="${not empty statements}">
            <h1>Applications</h1>
            <table cellpadding="0" cellspacing="0">
                <thead>
                <tr>
                    <th class="ordered-by">Id</th>
                    <th>SubmitDate</th>
                    <th>ChangeDate</th>
                    <th>Status</th>
                    <th>&nbsp;</th>
                </tr>
                </thead>

                    <tbody>
                    <c:forEach items="${statements}" var="app">
                        <tr data-application-id="${app.id}">
                            <td>${app.id}</td>
                            <td>${app.submitDate}</td>
                            <td>${app.changedDate}</td>
                            <td>${app.status}</td>
                            <td class="buttons">
                                <a class="button positive"
                                   href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/details.jsp?id=${app.id}">Details</a>

                                <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                                      method="post">
                                    <button class="positive" type="submit">Approve</button>
                                    <input type="hidden" name="status" value="approved"/>
                                </form>
                                <form action="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/api/content/ivis/${app.id}"
                                      method="post">
                                    <button class="negative" type="submit">Decline</button>
                                    <input type="hidden" name="status" value="declined"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

            </table>
            <div class="buttons">
                <a class="button positive"
                   href="<%=Imcms.getServerProperties().getProperty("ClientAddress")%>/applications/import">Import</a>
            </div>
            </c:if>
        </div>
    </div>

</div>

<%--<imcms:menu no='1' docId="1001" label='<br/><br/>Meny (punktlista)'>--%>
<%--<ul>--%>
<%--<imcms:menuloop>--%>
<%--<imcms:menuitem>--%>
<%--<li style="padding-bottom:5px; color:green;"><imcms:menuitemlink><c:out--%>
<%--value="${menuitem.document.headline}"/></imcms:menuitemlink>--%>
<%--<imcms:menuloop>--%>
<%--<imcms:menuitem>--%>
<%--<div style="padding-bottom:5px; color:green;">--%>
<%--<imcms:menuitemlink><c:out--%>
<%--value="${menuitem.document.headline}"/></imcms:menuitemlink>--%>
<%--</div>--%>
<%--</imcms:menuitem>--%>
<%--</imcms:menuloop>--%>
<%--</li>--%>
<%--</imcms:menuitem>--%>
<%--</imcms:menuloop>--%>
<%--</ul>--%>
<%--</imcms:menu>--%>
<%--<imcms:admin/>--%>
<%--<div class="col-sm-6">--%>
<%--<div class="mb-md">--%>
<%--<button id="addToTable" class="btn btn-primary" onclick="addApplication()">Add <i class="fa fa-plus"></i></button>--%>
<%--</div>--%>
<%--</div>--%>

</body>
</html>