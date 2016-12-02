Walkthrough: iVIS Server Configuration
======================================

    * `Create or edit iVIS client`_
    * `Create or edit iVIS role`_

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

The id of User which obtain client.

Secret
~~~~~~

The password of client for access to resources.

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

Role (roles) that consist from set of permissions for API access.

Described next at `Create or edit iVIS role`_ .

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

.. _`Create or edit iVIS role`:

Create or edit iVIS role
------------------------

Role in iVIS is a set of permissions to access method API, separated into groups by entity name.

.. image:: /images/ivisRole.png

Name
~~~~

The name of role.

For
~~~

Define role purpose.

List of permissions
~~~~~~~~~~~~~~~~~~~

Permission represents as method API with detail description.

.. image:: /images/permissions.png

.. image:: /images/permission.png

.. tip::
    Checkbox near entity name has three state. It indicates that in group checked no one/all/some.
    Also it provides possibility to check/uncheck all permissions in any group.





























