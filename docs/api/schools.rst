Schools
=======

``(implementation of School entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get school`_
    * `Get schools`_
    * `Save school`_
    * `Save schools`_
    * `Update school`_
    * `Delete school`_
    * `Get school or schools by name`_

.. _`Get school`:

Get school
----------

URL:
~~~~
    */schools/{id}*

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
        #. school_id(STRING)
        #. school_classes(ARRAY)
           type of `SchoolClass <http://docs.ivis.se/en/latest/api/schoolclass.html>`_
        #. after_school_center_sections(ARRAY)
           type of `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/afterschoolcentersection.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "school_id" : "",
      "school_classes" : {},
      "after_school_center_sections" : {}
    }

.. _`Get schools`:

Get schools
-----------

URL:
~~~~
    */schools*

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

    Array consists of objects from `Get school`_ method

Save school
-----------

URL:
~~~~
    */schools*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(School)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(School)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save schools
------------

URL:
~~~~
    */schools*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(School)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(School)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update school`:

Update school
-------------

URL:
~~~~
    */schools/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(School)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(School)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete school`:

Delete school
-------------

URL:
~~~~
    */schools/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(School)*

.. note::

    you receive deleted object

.. _`Get school or schools by name`:

Get school or schools by name
-----------------------------

URL:
~~~~
    */schools*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (School)*

