Access to protected resources
=============================

Prerequisites
-------------

    * `Tokens flow <http://docs.ivis.se/en/latest/sdk/routines/tokens_flow.html>`_

You can limit access to specific urls, or some code areas on JSP page. iVIS provides SDK in both case.

Both variants has optional parameter roles (String), it is comma separated list of roles that access give user access
to protected resources.

Filter
------

Java config
~~~~~~~~~~~

`BeansContext.java <http://docs.ivis.se/en/latest/sdk/routines/code/BeansContext.java>`_

.. literalinclude:: /sdk/routines/code/BeansContext.java
    :language: java
    :linenos:
    :lines: 39-54

XML config
~~~~~~~~~~

You need write in web.xml following.

.. code-block:: xml
    :linenos:

    <filter>
        <filter-name>ivisAuthorizedFilter</filter-name>
        <filter-class>imcode.services.filter.IvisAuthorizedFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>ivisAuthorizedFilter</filter-name>
        <url-pattern>/persons/*</url-pattern>
        <url-pattern>/pupils/*</ur l-pattern>
        <init-param>
           <param-name>roles</param-name>
           <param-value>ROLE_ADMIN,ROLE_DEVELOPER</param-value>
        </init-param>
    </filter-mapping>

Tag
---

To know if user login on JSP you can invoke special tag <ivis:authorized> with optional parameter role.

.. code-block:: jsp
    :linenos:

    <%@taglib prefix="ivis" uri="ivis.sdk" %>

    <ivis:authorized>
        Information for authorized users
    </ivis:authorized>

    ...

    <ivis:authorized roles="ROLE_ADMIN">
        Information for authorized users in admin role
    </ivis:authorized>

.. important::

    You can use this two cases if you have permission to use method getCurrent user.
    After invoking Filter or tag in session persisted user object ("loggedInUser" key to parameter).
