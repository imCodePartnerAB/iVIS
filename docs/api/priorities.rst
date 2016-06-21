Priorities
==========

``(implementation of Priority entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get priorities`_

.. _`Get priorities`:

Get priorities
--------------

URL:
~~~~
    */priorities/{id}*

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
        "name" : "priority"
    }
