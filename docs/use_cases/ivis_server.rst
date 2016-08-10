iVIS Server Configuration
=========================

    * `Create or edit iVIS client`_

.. _`Create or edit iVIS client`:

Create/Edit iVIS client
-----------------------

Client is any client application that connects to the iVIS Server and works with data.
To create a client go to the clients list and click Create button.

.. image:: /images/ivisClient.png

Name
~~~~

The name of client.

Resources
~~~~~~~~~

The name of resource which client obtain.

Owner
~~~~~

The name of User which has got client.

Secret
~~~~~~

The password of client for access to resources

Scope
~~~~~

Scope for client activity.

There are 3 parameters for scope:

.. envvar:: read

    Requires only read data of resources (GET requests)

.. envvar:: write

    Requires change data of resources (POST, PUT, DELETE)

.. note::
    If you want create client based on API, you will be enough read and write

.. envvar:: execute

    Requires change data of resources through proxy. This client needs ivis-core and ivis-sdk dependencies.

Authorized Grant Types
~~~~~~~~~~~~~~~~~~~~~~

This section allows choose ways for obtain access token for client

.. envvar:: authorization_code

    About this you can read at `Authorization Code Grant <https://tools.ietf.org/html/rfc6749#section-4.1>`_

.. envvar:: implicit

    About this you can read at `Implicit Grant <https://tools.ietf.org/html/rfc6749#section-4.2>`_

.. envvar:: client_credentials

    About this you can read at `Client Credentials Grant <https://tools.ietf.org/html/rfc6749#section-4.4>`_

.. envvar:: password

    About this you can read at `Resource Owner Password Credentials Grant <https://tools.ietf.org/html/rfc6749#section-4.3>`_

Registered Redirect Uri
~~~~~~~~~~~~~~~~~~~~~~~

URL for access token or authorization code response.

Roles
~~~~~

.. envvar:: ROLE_ADMIN

    Can edit/view information

.. envvar:: ROLE_USER

    Can only view information

Access Token Validity(sec)
~~~~~~~~~~~~~~~~~~~~~~~~~~

Number of seconds after which the access token expires, and is no longer valid.

Refresh Token Validity(sec)
~~~~~~~~~~~~~~~~~~~~~~~~~~~

Number of seconds after which the refresh token expires, and is no longer valid.

.. note::

    Refresh token validity must be longer than access.

.. seealso::

    Read about `Access Token <https://tools.ietf.org/html/rfc6749#section-1.4>`_ and
    `Refresh Token <https://tools.ietf.org/html/rfc6749#section-1.5>`_
































