Results
=======

Run client application
----------------------

Run iVIS server from `tutorial <http://docs.ivis.se/en/latest/quick_start/walkthrough_install_ivis_server.html>`_.

.. seealso::

    You need configure permissions for client sample application.
    `This guide <http://docs.ivis.se/en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ will be helpful.

Application must be started on port 8080.

Clone project from Github (https://github.com/imCodePartnerAB/iVIS-Client-Sample.git ).

Execute Maven package

.. code-block:: bash

    mvn package spring-boot:run

Application must be started on port 8085 with context path "/client".

Check all
---------

At url http://localhost:8085/client we see:

.. image:: /images/developing_client/welcome.png

Click Login.

.. image:: /images/developing_client/login.png

Click Create school class.

.. image:: /images/developing_client/servicesClasses.png

Fill test data.

.. image:: /images/developing_client/fillFields.png

.. image:: /images/developing_client/completeFillFields.png

Click Create.

.. image:: /images/developing_client/created.png

Security test
-------------

Open new incognito window.

Type http://localhost:8085/client/services/classes.

In logs you get following

.. code-block:: logs

    SEVERE: Servlet.service() for servlet [dispatcherServlet] in context with path [/client] threw exception
    error="unauthorized_user", error_description="Token isn't good."
        at imcode.services.filter.IvisAuthorizedFilter.doFilter(IvisAuthorizedFilter.java:42)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:89)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:77)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)
        at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)
        at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:192)
        at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:165)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:198)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:108)
        at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:472)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:140)
        at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:79)
        at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:87)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:349)
        at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:784)
        at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:66)
        at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:802)
        at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1410)
        at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
        at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
        at java.lang.Thread.run(Thread.java:745)

    INFO : com.imcode.controllers.IvisAuthorizationController - User unauthorized!
    DEBUG: com.imcode.controllers.IvisAuthorizationController - http://localhost:8080/logout.do?redirect_url=http%3A%2F%2Flocalhost%3A8085%2Fclient%2Flogin

And you will be redirected to iVIS login page.

.. image:: /images/developing_client/ivisLogin.png

Okey let's see our welcome page (http://localhost:8085/client/ ).

You can't see link "Create school class" because it placed under protected resource:

.. code-block:: jsp

    <ivis:authorized>
        <a href="${pageContext.servletContext.contextPath}/services/classes">Create school class</a>
    </ivis:authorized>











