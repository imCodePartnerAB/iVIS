SchoolClasses
=============

``(implementation of SchoolClass entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetByName`_
    * `GetFirstByName`_
    * `SaveAll`_
    * `SaveAllAndReturnIds`_
    * `Search`_
    * `SearchFirst`_
    * `Update`_

.. _`Create`:

Create
------

URL:
~~~~
    */api/v1/{format}/schoolclasses*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/schoolclasses/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/schoolclasses*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: ids

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/schoolclasses/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/schoolclasses*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetByName`:

GetByName
---------

URL:
~~~~
    */api/v1/{format}/schoolclasses*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: name

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetFirstByName`:

GetFirstByName
--------------

URL:
~~~~
    */api/v1/{format}/schoolclasses*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: name, first

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/schoolclasses/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/schoolclasses/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:
        ARRAY<NUMBER>
.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/schoolclasses/search*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `SearchCriteries$SearchCriteriaResult <http://docs.ivis.se/en/latest/api/entities/SearchCriteries$SearchCriteriaResult.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/schoolclasses/search/first*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `SearchCriteries$SearchCriteriaResult <http://docs.ivis.se/en/latest/api/entities/SearchCriteries$SearchCriteriaResult.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/schoolclasses/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_day_start(NUMBER(Date representation wrapped))
        #. school_day_end(NUMBER(Date representation wrapped))
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. diaries(ARRAY< `Diary <http://docs.ivis.se/en/latest/api/entities/Diary.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

