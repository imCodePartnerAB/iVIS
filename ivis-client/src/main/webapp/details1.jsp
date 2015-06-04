<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" pageEncoding="UTF-8" %>
<%@ page import="com.imcode.imcms.addon.ivisAPI.entities.Application_" %>
<%@ page import="imcode.server.Imcms" %>
<%@ page import="imcode.server.parser.ParserParameters" %>
<%@ page import="org.apache.oro.text.perl.Perl5Util" %>
<%@ page import="org.springframework.http.HttpEntity" %>
<%@ page import="org.springframework.http.HttpHeaders" %>
<%@ page import="org.springframework.http.HttpMethod" %>
<%@ page import="org.springframework.http.ResponseEntity" %>
<%@ page import="org.springframework.security.oauth2.provider.client.BaseClientDetails" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="java.io.IOException" %>

<%@taglib prefix="imcms" uri="imcms" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<imcms:variables/>
<%

    // TODO: Add support for imCMS versions > 5

    String documentationUrl = "@documentationwebappurl@";

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
<!DOCTYPE html>
<html>
<head>

    <!-- Basic -->
    <meta charset="utf-8">
    <title><c:out value="${document.headline}"/></title>
    <meta name="keywords" content="HTML5 Template"/>
    <meta name="description" content="Porto - Responsive HTML5 Template">
    <meta name="author" content="okler.net">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Web Fonts  -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800%7CShadows+Into+Light"
          rel="stylesheet" type="text/css">
    <!-- Vendor CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/fontawesome/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/owlcarousel/owl.carousel.min.css"
          media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/owlcarousel/owl.theme.default.min.css"
          media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/magnific-popup/magnific-popup.css"
          media="screen">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-elements.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-blog.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-shop.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/theme-animate.css">

    <!-- Current Page CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/rs-plugin/css/settings.css" media="screen">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/vendor/circle-flip-slideshow/css/component.css"
          media="screen">

    <!-- Skin CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/skins/default.css">

    <!-- Theme Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">

    <!-- Head Libs -->
    <script src="${pageContext.request.contextPath}/vendor/modernizr/modernizr.js"></script>

    <!--[if IE]>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ie.css">
    <![endif]-->

    <!--[if lte IE 8]>
    <script src="${pageContext.request.contextPath}/vendor/respond/respond.js"></script>
    <script src="${pageContext.request.contextPath}/vendor/excanvas/excanvas.js"></script>
    <![endif]-->

</head>
<body>
<div class="body">
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <div role="main" class="main">
        <imcms:admin/>
        <section class="page-top">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="breadcrumb">
                            <li><a href="#">Home</a></li>
                            <li class="active">${document.headline}</li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <h1>${document.headline}</h1>
                    </div>
                </div>
            </div>
        </section>

        <div class="container">

            <div class="row">
                <div class="col-md-3">
                    <aside class="sidebar">

                        <h4>Categories</h4>
                        <ul class="nav nav-list primary push-bottom">
                            <li><a href="/applications">My applications</a></li>
                        </ul>

                        <hr>
                        <imcms:text no="1" label="Head 4 (html)" formats="text,html" rows="2" pre='<h4>' post='</h4>'/>
                        <imcms:text no="2" label="Description (html)" formats="text,html" rows="2" pre='<div>'
                                    post='</div>'/>
                    </aside>
                </div>
                <div class="col-md-9">

                    <imcms:text no="3" label="Head 2 (html)" formats="text,html" rows="2" pre='<h2>' post='</h2>'/>
                    <imcms:text no="4"
                                label="Description (html)"
                                formats="text,html"
                                pre='<div class="row"><div class="col-md-12"><p class="lead">'
                                post='</p></div></div>'
                            />

                    <hr class="tall">
                    <imcms:text no="5"
                                label="Info (html)"
                                formats="text,html"
                                pre='<div class="row"><div class="col-md-12">'
                                post='</div></div>'
                            />

                    <h1>Application</h1>
                    <%
                        String id = null;
                        try {
                            id = request.getParameterMap().get("id")[0];
                        } catch (Exception ignore) {
                        }
                        if (id != null) {
                            String address = "http://" + request.getServerName() + ":" + request.getServerPort();
                            request.setAttribute("address", address);
                            request.setAttribute("id", id);
                            RestTemplate restTemplate = new RestTemplate();
                            HttpHeaders requestHeaders = new HttpHeaders();
                            requestHeaders.add("Cookie", "JSESSIONID=" + request.getSession().getId());
                            HttpEntity httpEntity = new HttpEntity(null, requestHeaders);
                            ResponseEntity<Boolean> hasToken = restTemplate.exchange(address + "/web2/ivis/hastoken", HttpMethod.GET, httpEntity, Boolean.class);
                            boolean result = hasToken.getBody();

                            if (result) {%>
                    <%
                        String applicationString = restTemplate.exchange(address + "/web2/ivis/applications/" + id, HttpMethod.GET, httpEntity, String.class).getBody();
                        BaseClientDetails app = null;
                        ObjectMapper jsonMapper = new ObjectMapper();

//
                        try {
                            Application_ node = jsonMapper.readValue(applicationString, Application_.class);
                            request.setAttribute("app", node.getApplication());
                        } catch (IOException ignore) {
                            ignore.printStackTrace();
                        }
                    %>
                    <%} else {%>
                    <script>location.href = "${address}/web2/ivis/go?to=/applications/details?id=${id}" </script>
                    <%
                            }
                        }
                    %>

                    <c:if test="${not empty message}">
                        <div id="message" class="${message.type}">${message.message}</div>
                    </c:if>
                    <c:if test="${not empty app}">
                        <table class="table table-striped">
                            <tbody>
                            <tr>
                                <td>Id</td>
                                <td>${app.clientId}</td>
                            </tr>
                            <tr>
                                <td>Resource Ids</td>
                                <td>${app.resourceIds}</td>
                            </tr>
                            <tr>
                                <td>Secret</td>
                                <td>${app.clientSecret}</td>
                            </tr>
                            <tr>
                                <td>Scope</td>
                                <td>${app.scope}</td>
                            </tr>
                            <tr>
                                <td>Authorized Grant Types</td>
                                <td>${app.authorizedGrantTypes}</td>
                            </tr>
                            <tr>
                                <td>Registered Redirect Uri</td>
                                <td>${app.registeredRedirectUri}</td>
                            </tr>
                            <tr>
                                <td>Authorities</td>
                                <td>${app.authorities}</td>
                            </tr>
                            <tr>
                                <td>Access Token Validity(sec)</td>
                                <td>${app.accessTokenValiditySeconds}</td>
                            </tr>
                            <tr>
                                <td>Refresh Token Validity(sec)</td>
                                <td>${app.refreshTokenValiditySeconds}</td>
                            </tr>
                            <tr>
                                <td>Auto aproove</td>
                                <td>${app.isAutoApprove(app.scope)}</td>
                            </tr>
                            </tbody>
                        </table>
                        <a href="/applications/edit?id=${app.clientId}" class="on-default edit-row"><i class="fa fa-pencil"> Edit</i></a>
                        <%--<a href="/${application.clientId}?form">Edit</a>--%>
                    </c:if>
                </div>

            </div>

        </div>

    </div>


    <jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</div>

<!-- Vendor -->
<%
    ParserParameters parserParameters = ParserParameters.fromRequest(request);
    if (!Imcms.getUser().hasAdminPanelForDocument(parserParameters.getDocumentRequest().getDocument())) {
%>
<script src="${pageContext.request.contextPath}/vendor/jquery/jquery.js"></script>
<%}%>
<script src="${pageContext.request.contextPath}/vendor/jquery.appear/jquery.appear.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery.easing/jquery.easing.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery-cookie/jquery-cookie.js"></script>
<script src="${pageContext.request.contextPath}/vendor/bootstrap/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/vendor/common/common.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery.validation/jquery.validation.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery.stellar/jquery.stellar.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery.easy-pie-chart/jquery.easy-pie-chart.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jquery.gmap/jquery.gmap.js"></script>
<script src="${pageContext.request.contextPath}/vendor/isotope/jquery.isotope.js"></script>
<script src="${pageContext.request.contextPath}/vendor/owlcarousel/owl.carousel.js"></script>
<script src="${pageContext.request.contextPath}/vendor/jflickrfeed/jflickrfeed.js"></script>
<script src="${pageContext.request.contextPath}/vendor/magnific-popup/jquery.magnific-popup.js"></script>
<script src="${pageContext.request.contextPath}/vendor/vide/vide.js"></script>

<!-- Theme Base, Components and Settings -->
<script src="${pageContext.request.contextPath}/js/theme.js"></script>

<!-- Specific Page Vendor and Views -->
<script src="${pageContext.request.contextPath}/vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/rs-plugin/js/jquery.themepunch.revolution.min.js"></script>
<script src="${pageContext.request.contextPath}/vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
<script src="${pageContext.request.contextPath}/js/views/view.home.js"></script>

<!-- Theme Custom -->
<script src="${pageContext.request.contextPath}/js/custom.js"></script>

<!-- Theme Initialization Files -->
<script src="${pageContext.request.contextPath}/js/theme.init.js"></script>
<script type="text/javascript">
    $("#mainMenu").find("li").each(function (position, element) {
        element = $(element);
        if (element.find("ul").children().length > 0) {
            element.addClass("dropdown");
            element.children("a").addClass("dropdown-toggle").append($("<i>").addClass("fa fa-angle-down"));
        }
    })
</script>
<!-- Google Analytics: Change UA-XXXXX-X to be your site's ID. Go to http://www.google.com/analytics/ for more information.
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-12345678-1']);
    _gaq.push(['_trackPageview']);

    (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
    })();

</script>
 -->

</body>
</html>