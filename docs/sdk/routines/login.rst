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

Talking is a good but let's coding.

Prerequisites
-------------

.. code-block:: java







