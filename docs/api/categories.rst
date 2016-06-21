Categories
==========

``(implementation of Category entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get categories`_

.. _`Get categories`:

Get categories
--------------

URL:
~~~~
    */categories/{id}*

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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name" : "category"
    }
