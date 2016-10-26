Statuses
========

``(implementation of Status entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get status`_
    * `Get statuses`_
    * `Save status`_
    * `Save statuses`_
    * `Update status`_
    * `Delete status`_

.. _`Get status`:

Get status
----------

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
        #. id(NUMBER)
        #. name(NULL)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : null
    }

.. _`Get statuses`:

Get statuses
------------

URL:
~~~~
    */statuses*

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

    Array consists of objects from `Get status`_ method

Save status
-----------

URL:
~~~~
    */statuses*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Status)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Status)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save statuses
-------------

URL:
~~~~
    */statuses*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Status)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Status)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update status`:

Update status
-------------

URL:
~~~~
    */statuses/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Status)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Status)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete status`:

Delete status
-------------

URL:
~~~~
    */statuses/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Status)*

.. note::

    you receive deleted object

