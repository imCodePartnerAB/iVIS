PersonRoles
===========

``(implementation of PersonRole entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetDistinctRolesOfCurrentUser`_
    * `GetDistinctSchoolClassesOfCurrentUser`_
    * `GetDistinctSchoolsOfCurrentUser`_
    * `GetPersonRolesOfCurrentUser`_
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
    */api/v1/{format}/personroles*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `PersonRole <http://docs.ivis.se/en/latest/api/entities/PersonRole.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/personroles/{id}*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/personroles*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/personroles/{id}*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/personroles*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`GetDistinctRolesOfCurrentUser`:

GetDistinctRolesOfCurrentUser
-----------------------------

URL:
~~~~
    */api/v1/{format}/personroles/workroles/ofcurrentuser*

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

        #. name(STRING)
        #. id(NUMBER)

.. _`GetDistinctSchoolClassesOfCurrentUser`:

GetDistinctSchoolClassesOfCurrentUser
-------------------------------------

URL:
~~~~
    */api/v1/{format}/personroles/schoolclasses/ofcurrentuser*

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

.. _`GetDistinctSchoolsOfCurrentUser`:

GetDistinctSchoolsOfCurrentUser
-------------------------------

URL:
~~~~
    */api/v1/{format}/personroles/schools/ofcurrentuser*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetPersonRolesOfCurrentUser`:

GetPersonRolesOfCurrentUser
---------------------------

URL:
~~~~
    */api/v1/{format}/personroles/ofcurrentuser*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/personroles/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `PersonRole <http://docs.ivis.se/en/latest/api/entities/PersonRole.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/personroles/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `PersonRole <http://docs.ivis.se/en/latest/api/entities/PersonRole.html>`_ >

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
    */api/v1/{format}/personroles/search*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/personroles/search/first*

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

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/personroles/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `PersonRole <http://docs.ivis.se/en/latest/api/entities/PersonRole.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. role(OBJECT< `WorkRole <http://docs.ivis.se/en/latest/api/entities/WorkRole.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. date_from(NUMBER(Date representation wrapped))
        #. date_to(NUMBER(Date representation wrapped))
        #. id(NUMBER)

