SchoolClasses
=============

``(implementation of SchoolClass entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get schoolClass`_
    * `Get schoolClasses`_
    * `Save schoolClass`_
    * `Update schoolClass`_
    * `Delete schoolClass`_
    * `Get schoolClass or schoolClasses by name`_

.. _`Get schoolClass`:

Get schoolClass
---------------

URL:
~~~~
    */schoolclasses/{id}*

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
        #. id (NUMBER)
        #. name (STRING)
        #. school (OBJECT)
            type of `School <http://docs.ivis.se/en/latest/api/school.html>`_
        #. pupils (ARRAY)
            type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_
        #. diaries (ARRAY)
             type of `Diary <http://docs.ivis.se/en/latest/api/diary.html>`_
        #. school_day_start (STRING)
        #. school_day_end (STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
       "id":10,
       "name":"",
       "school_day_start":"08:00:00",
       "school_day_end":"15:00:00",
       "school":{  },
       "pupils":[  ],
       "diaries":[  ],
       "diary_list":[  ]
    }

.. _`Get schoolClasses`:

Get schoolClasses
-----------------

URL:
~~~~
    */schoolclasses*

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
    Array consists of objects from `Get schoolClass`_ method

.. _`Save schoolClass`:

Save schoolClass
----------------

URL:
~~~~
    */schoolclasses*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get schoolClass`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Save schoolClasses`:

Save schoolClasses
------------------

URL:
~~~~
    */schoolclasses*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *ARRAY*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY*

.. _`Update schoolClass`:

Update schoolClass
------------------

URL:
~~~~
    */schoolclasses/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get schoolClass`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete  schoolClass`:

Delete schoolClass
------------------

URL:
~~~~
    */schoolclasses/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Get schoolClass or schoolClasses by name`:

Get schoolClass or schoolClasses by name
----------------------------------------

URL:
~~~~
    */schoolclasses*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (SchoolClass)*

