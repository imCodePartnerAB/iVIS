Users
=====

``(implementation of User entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get user`_
    * `Get users`_
    * `Save user`_
    * `Save users`_
    * `Update user`_
    * `Delete user`_
    * `Get user or users by personal id`_
    * `Get current`_
    * `Get logged in`_

.. _`Get user`:

Get user
--------

URL:
~~~~
    */users/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties:*
        #. id(NUMBER)
        #. name(STRING)
        #. person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. roles(ARRAY)
           type of `Role <http://docs.ivis.se/en/latest/api/role.html>`_
        #. saml2_id(STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "person" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "roles" : [ ],
      "saml2_id" : ""
    }

.. _`Get users`:

Get users
---------

URL:
~~~~
    */users*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. seealso::

    Array consists of objects from `Get user`_ method

Save user
---------

URL:
~~~~
    */users*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(User)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(User)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save users
----------

URL:
~~~~
    */users*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(User)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(User)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update user`:

Update user
-----------

URL:
~~~~
    */users/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(User)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(User)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete user`:

Delete user
-----------

URL:
~~~~
    */users/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(User)*

.. note::

    you receive deleted object

.. _`Get user or users by personal id`:

Get user or users by personal id
-----------------------------

URL:
~~~~
    */users*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (User)*

.. _`Get current`:

Get current
-----------

URL:
~~~~
    */users/current*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Get logged in`:

Get logged in
-------------

URL:
~~~~
    */users/loggedin*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties:*
        #. id (Number)
        #. person (Object)
        #. roles (Array)
           type of `Role <http://docs.ivis.se/en/latest/api/role.html>`_

.. note::

    Response object is different from user standard object!!!

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id" : 1,
        "person" : {},
        "roles" : []
    }



