AcademicYears
=============

``(implementation of AcademicYear entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get academicYear`_
    * `Get academicYears`_
    * `Save academicYear`_
    * `Save academicYears`_
    * `Update academicYear`_
    * `Delete academicYear`_
    * `Get academicYear or academicYears by name`_

.. _`Get academicYear`:

Get academicYear
----------------

URL:
~~~~
    */academicyears/{id}*

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

.. _`Get academicyears`:

Get academicyears
-----------------

URL:
~~~~
    */academicyears*

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

    Array consists of objects from `Get academicYear`_ method

Save academicYear
-----------------

URL:
~~~~
    */academicyears*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(AcademicYear)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AcademicYear)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save academicyears
------------------

URL:
~~~~
    */academicyears*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(AcademicYear)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(AcademicYear)*

On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update academicYear`:

Update academicYear
-------------------

URL:
~~~~
    */academicyears/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(AcademicYear)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AcademicYear)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete academicYear`:

Delete academicYear
-------------------

URL:
~~~~
    */academicyears/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AcademicYear)*

.. note::

    you receive deleted object

.. _`Get academicYear or academicYears by name`:

Get academicYear or academicYears by name
-----------------------------------------

URL:
~~~~
    */academicyears*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (AcademicYear)*

