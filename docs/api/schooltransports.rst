SchoolTransports
================

``(implementation of SchoolTransport entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get schoolTransport`_
    * `Get schoolTransports`_
    * `Save schoolTransport`_
    * `Save schoolTransports`_
    * `Update schoolTransport`_
    * `Delete schoolTransport`_
    * `Get schoolTransport or schoolTransports by name`_

.. _`Get schoolTransport`:

Get schoolTransport
-------------------

URL:
~~~~
    */schooltransports/{id}*

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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : ""
    }

.. _`Get schooltransports`:

Get schooltransports
--------------------

URL:
~~~~
    */schooltransports*

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

    Array consists of objects from `Get schoolTransport`_ method

Save schoolTransport
--------------------

URL:
~~~~
    */schooltransports*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(SchoolTransport)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(SchoolTransport)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save schooltransports
---------------------

URL:
~~~~
    */schooltransports*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(SchoolTransport)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(SchoolTransport)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update schoolTransport`:

Update schoolTransport
----------------------

URL:
~~~~
    */schooltransports/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(SchoolTransport)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(SchoolTransport)*

.. note::
    
    property will be updated, if you don't want update property it need set null

.. _`Delete schoolTransport`:

Delete schoolTransport
----------------------

URL:
~~~~
    */schooltransports/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(SchoolTransport)*

.. note::

    you receive deleted object

.. _`Get schoolTransport or schoolTransports by name`:

Get schoolTransport or schoolTransports by name
-----------------------------------------------

URL:
~~~~
    */schooltransports*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (SchoolTransport)*

