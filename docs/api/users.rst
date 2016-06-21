Users
=====

``(implementation of User entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get logged in`_

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

.. note::
    roles consist of objects with properties: *id* and *name*

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id" : 1,
        "person" : {},
        "roles" : []
    }



