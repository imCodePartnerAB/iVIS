Categories
==========

``(implementation of Category entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get category`_
    * `Get categories`_
    * `Save category`_
    * `Save categories`_
    * `Update category`_
    * `Delete category`_

.. _`Get category`:

Get category
------------

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
        #. id(NUMBER)
        #. name(STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : ""
    }

.. _`Get categories`:

Get categories
--------------

URL:
~~~~
    */categories*

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

    Array consists of objects from `Get category`_ method

Save category
-------------

URL:
~~~~
    */categories*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Category)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Category)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save categories
---------------

URL:
~~~~
    */categories*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Category)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Category)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update category`:

Update category
---------------

URL:
~~~~
    */categories/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Category)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Category)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete category`:

Delete category
---------------

URL:
~~~~
    */categories/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Category)*

.. note::

    you receive deleted object

