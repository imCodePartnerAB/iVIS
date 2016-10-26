EntityVersions
==============

``(implementation of EntityVersion entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get entityVersion`_
    * `Get entityVersions`_
    * `Save entityVersion`_
    * `Save entityVersions`_
    * `Update entityVersion`_
    * `Delete entityVersion`_
    * `Get entityVersion or entityVersions by personal id`_
    * `Get entityVersion or entityVersions by name`_

.. _`Get entityVersion`:

Get entityVersion
-----------------

URL:
~~~~
    */entityversions/{id}*

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
        #. entity_id(NUMBER)
        #. entity_class(NULL)
        #. entity(NULL)
        #. timestamp(NUMBER)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "entity_id" : 0,
      "entity_class" : null,
      "entity" : null,
      "timestamp" : 1477471556916
    }

.. _`Get entityversions`:

Get entityversions
------------------

URL:
~~~~
    */entityversions*

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
    
Array consists of objects from `Get entityVersion`_ method

Save entityVersion
------------------

URL:
~~~~
    */entityversions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(EntityVersion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(EntityVersion)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save entityversions
-------------------

URL:
~~~~
    */entityversions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(EntityVersion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(EntityVersion)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update entityVersion`:

Update entityVersion
--------------------

URL:
~~~~
    */entityversions/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(EntityVersion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(EntityVersion)*

.. note::
    
property will be updated, if you don't want update property it need set null

.. _`Delete entityVersion`:

Delete entityVersion
--------------------

URL:
~~~~
    */entityversions/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(EntityVersion)*

.. note::
    you receive deleted object

.. _`Get entityVersion or entityVersions by personal id`:

Get entityVersion or entityVersions by personal id
-----------------------------------------------

URL:
~~~~
    */entityversions

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (EntityVersion)*

.. _`Get entityVersion or entityVersions by name`:

Get entityVersion or entityVersions by name
-------------------------------------------

URL:
~~~~
    */entityversions

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (EntityVersion)*

