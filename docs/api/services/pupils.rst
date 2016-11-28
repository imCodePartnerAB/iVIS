Pupils
======

``(implementation of Pupil entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetAllPupils`_
    * `GetByPersonalId`_
    * `GetFirstByPersonalId`_
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
    */api/v1/{format}/pupils*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/pupils/{id}*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/pupils*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/pupils/{id}*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/pupils*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`GetAllPupils`:

GetAllPupils
------------

URL:
~~~~
    */api/v1/{format}/pupils/all*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`GetByPersonalId`:

GetByPersonalId
---------------

URL:
~~~~
    */api/v1/{format}/pupils*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: personalId

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`GetFirstByPersonalId`:

GetFirstByPersonalId
--------------------

URL:
~~~~
    */api/v1/{format}/pupils*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: personalId, first

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/pupils/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/pupils/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >

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
    */api/v1/{format}/pupils/search*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/pupils/search/first*

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
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/pupils/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. contact_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. class_placement_from(NUMBER(Date representation wrapped))
        #. class_placement_to(NUMBER(Date representation wrapped))
        #. school_class(OBJECT< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. school(OBJECT< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >)
        #. academic_year(OBJECT< `AcademicYear <http://docs.ivis.se/en/latest/api/entities/AcademicYear.html>`_ >)
        #. guardians(ARRAY< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >)
        #. truancies(ARRAY< `Truancy <http://docs.ivis.se/en/latest/api/entities/Truancy.html>`_ >)
        #. after_school_center_section(OBJECT< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. school_center_schema(ARRAY< `AfterSchoolCenterSchema <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSchema.html>`_ >)
        #. id(NUMBER)

