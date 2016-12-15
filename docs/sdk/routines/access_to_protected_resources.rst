Access to protected resources
=============================

Prerequisites
-------------

    * `Tokens flow <http://docs.ivis.se/en/latest/sdk/routines/tokens_flow.html>`_

You can limit access to specific urls, or some code areas on JSP page. iVIS provides SDK in both cases.

Both variants has optional parameter roles (String). "role" it is comma separated list of roles which gives user access
to protected resources.

Filter
------

Beans config
~~~~~~~~~~~~

.. code-block:: java

    @Bean(name = "ivisAuthorizedFilter")
    public Filter ivisAuthorizedFilter() {
        IvisAuthorizedFilter ivisAuthorizedFilter = new IvisAuthorizedFilter();
        return ivisAuthorizedFilter;
    }

    @Bean
    public FilterRegistrationBean ivisAuthorizedFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ivisAuthorizedFilter());
        registration.addUrlPatterns("/services/classes/*");
        registration.setName("ivisAuthorizedFilter");
        registration.setOrder(1);
        return registration;
    }

Tag
---

To know if user login on JSP you can invoke special tag <ivis:authorized> with optional parameter role.

.. code-block:: jsp

    <%@taglib prefix="ivis" uri="ivis.sdk" %>

    ...

    <ivis:authorized>
        Information for authorized users
    </ivis:authorized>

    ...

    <ivis:authorized roles="ROLE_ADMIN">
        Information for authorized users in admin role
    </ivis:authorized>

.. important::

    You can use this two cases if you have permission to use method getCurrent user.
    After invoking Filter or tag with parameter role in session persisted user object ("loggedInUser" key to parameter).
