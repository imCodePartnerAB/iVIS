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

:download:`BeansContext.java </sdk/routines/code/IvisAuthorizationController.java>`

.. literalinclude:: /sdk/routines/code/IvisAuthorizationController.java
    :language: java
    :linenos:
    :lineno-start: 22
    :lines: 22-53













