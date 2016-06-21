Statuses
========

``(implementation of Status entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get statuses`_

.. _`Get statuses`:

Get statuses
------------

URL:
~~~~
    */statuses/{id}*

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
    #. name (String)

.. note::
    Property name has only values: "CREATING", "NEW", "ASSIGNED", "ARCHIVED"

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name" : "CREATING"
    }
