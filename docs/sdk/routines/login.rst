Login
=====

Prerequisites
-------------

    * `Configuration <http://docs.ivis.se/en/latest/sdk/routines/configuration.html>`_
    * `Authorization <http://docs.ivis.se/en/latest/api/authorization.html>`_

To login you need:

    #. send redirect in context based on HttpServlet (JSP, Spring MVC Controllers, RestControllers etc)
       to authorizeURI.
    #. send POST request with client credentials with code.
    #. receive access token.

Let's see how it looks like.

.. literalinclude:: /sdk/routines/code/LoginController.java
    :language: java
    :linenos:

To know if user login on JSP you can invoke special tag <ivis:authorized> with optional parameter role.

.. code-block:: jsp

    <%@taglib prefix="ivis" uri="ivis.sdk" %>

    <ivis:authorized>
        Information for authorized persons
    </ivis:authorized>

    ...

    <ivis:authorized role="ROLE_ADMIN">
        Information for user in admin role
    </ivis:authorized>

.. important::

    You can use this tag if you have permission to use method getCurrent user.









