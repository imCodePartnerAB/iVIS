Priorities
==========

``(implementation of Priority entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get priority`_
    * `Get priorities`_
    * `Save priority`_
    * `Save priorities`_
    * `Update priority`_
    * `Delete priority`_

.. _`Get priority`:

Get priority
------------

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
        #. id(NUMBER)
        #. name(STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : ""
    }

.. _`Get priorities`:

Get priorities
--------------

URL:
~~~~
    */priorities*

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

    Array consists of objects from `Get priority`_ method

Save priority
-------------

URL:
~~~~
    */priorities*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Priority)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Priority)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save priorities
---------------

URL:
~~~~
    */priorities*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Priority)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Priority)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update priority`:

Update priority
---------------

URL:
~~~~
    */priorities/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Priority)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Priority)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete priority`:

Delete priority
---------------

URL:
~~~~
    */priorities/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Priority)*

.. note::

    you receive deleted object


