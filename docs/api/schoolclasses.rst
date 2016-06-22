SchoolClasses
=============

``(implementation of SchoolClass entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get schoolClass`_
    * `Get schoolClasses`_
    * `Save schoolClass`_
    * `Update schoolClass`_
    * `Delete schoolClass`_

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
        #. id (Number)
        #. name (String)
        #. school (Object)
        #. pupils (Array)
        #. diaries (Array)
        #. school_day_start (String)
        #. school_day_end (String)

.. note::

    school relates to School entity

    pupils relates to Pupil entity

    diaries relates to Diary entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "school":{},
        "pupils":[],
        "diaries":[],
        "school_day_start":"00:00:00",
        "school_day_end":"00:00:00"
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

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

    *On server fill id*

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

