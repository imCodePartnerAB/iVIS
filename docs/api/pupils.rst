Pupils
======

``(implementation of Pupil entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get pupil`_
    * `Get pupils`_
    * `Get current guardian pupils`_
    * `Save pupil`_
    * `Save pupils`_
    * `Update pupil`_
    * `Delete pupil`_
    * `Get pupil or pupils by personal id`_

.. _`Get pupil`:

Get pupil
---------

URL:
~~~~
    */pupils/{id}*

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
        #. person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. contact_person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. class_placement_from(NUMBER)
        #. class_placement_to(NUMBER)
        #. school_class(OBJECT)
           type of `SchoolClass <http://docs.ivis.se/en/latest/api/schoolclass.html>`_
        #. school
           type of `School <http://docs.ivis.se/en/latest/api/school.html>`_
        #. academic_year(OBJECT)
           type of `AcademicYear <http://docs.ivis.se/en/latest/api/academicyear.html>`_
        #. guardians(ARRAY)
           type of `Guardian <http://docs.ivis.se/en/latest/api/guardian.html>`_
        #. truancies(ARRAY)
           type of `Truancy <http://docs.ivis.se/en/latest/api/truancy.html>`_
        #. after_school_center_section(OBJECT)
           type of `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/afterschoolcentersection.html>`_
        #. school_center_schema(ARRAY)
           type of `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/afterschoolcenterschema.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
       "id":10,
       "person":{  },
       "contact_person":{  },
       "class_placement_from":null,
       "class_placement_to":null,
       "school_class":{  },
       "school":null,
       "academic_year":null,
       "guardians":[  ],
       "truancies":[  ],
       "after_school_center_section":{  },
       "school_center_schema":[  ]
    }

.. _`Get current guardian pupils`:

Get pupils
----------

URL:
~~~~
    */pupils/all*

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

    Array consists of objects from `Get pupil`_ method

.. _`Get current guardian pupils`:

Get current guardian pupils
---------------------------

URL:
~~~~
    */pupils*

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

    Array consists of objects from `Get pupil`_ method

.. _`Save pupil`:

Save pupil
----------

URL:
~~~~
    */pupils*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Pupil)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Pupil)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

.. _`Save pupils`:

Save pupils
-----------

URL:
~~~~
    */pupils*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Pupil)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Pupil)*

Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update pupil`:

Update pupil
------------

URL:
~~~~
    */pupils/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Pupil)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Pupil)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete pupil`:

Delete pupil
------------

URL:
~~~~
    */pupils/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Pupil)*

.. note::

    you receive deleted object

.. _`Get pupil or pupils by personal id`:

Get pupil or pupils by personal id
----------------------------------

URL:
~~~~
    */pupils*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (Person)*


