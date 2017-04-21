Persons
=======

``(implementation of Person entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `FindByCriteria`_
    * `Get`_
    * `GetAll`_
    * `GetByPersonalId`_
    * `GetCurrentPerson`_
    * `GetFirstByPersonalId`_
    * `GetPersonRolesByPerson`_
    * `GetPersonRolesOfCurrentPerson`_
    * `GetSchoolClassesByPerson`_
    * `GetSchoolClassesOfCurrentPerson`_
    * `GetSchoolsByPerson`_
    * `GetSchoolsOfCurrentPerson`_
    * `GetWorkRolesByPerson`_
    * `GetWorkRolesOfCurrentPerson`_
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
    */api/v1/{format}/persons*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/persons/{id}*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/persons*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`FindByCriteria`:

FindByCriteria
--------------

URL:
~~~~
    */api/v1/{format}/persons*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: search_text, order_by

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/person/{id}*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/persons*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`GetByPersonalId`:

GetByPersonalId
---------------

URL:
~~~~
    */api/v1/{format}/persons*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`GetCurrentPerson`:

GetCurrentPerson
----------------

URL:
~~~~
    */api/v1/{format}/person/current*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`GetFirstByPersonalId`:

GetFirstByPersonalId
--------------------

URL:
~~~~
    */api/v1/{format}/persons*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`GetPersonRolesByPerson`:

GetPersonRolesByPerson
----------------------

URL:
~~~~
    */api/v1/{format}/person/{id}/personroles*

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

.. _`GetPersonRolesOfCurrentPerson`:

GetPersonRolesOfCurrentPerson
-----------------------------

URL:
~~~~
    */api/v1/{format}/person/current/personroles*

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

.. _`GetSchoolClassesByPerson`:

GetSchoolClassesByPerson
------------------------

URL:
~~~~
    */api/v1/{format}/person/{id}/schoolclasses*

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

.. _`GetSchoolClassesOfCurrentPerson`:

GetSchoolClassesOfCurrentPerson
-------------------------------

URL:
~~~~
    */api/v1/{format}/person/current/schoolclasses*

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

.. _`GetSchoolsByPerson`:

GetSchoolsByPerson
------------------

URL:
~~~~
    */api/v1/{format}/person/{id}/schools*

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

.. _`GetSchoolsOfCurrentPerson`:

GetSchoolsOfCurrentPerson
-------------------------

URL:
~~~~
    */api/v1/{format}/person/current/schools*

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

.. _`GetWorkRolesByPerson`:

GetWorkRolesByPerson
--------------------

URL:
~~~~
    */api/v1/{format}/person/{id}/workroles*

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

.. _`GetWorkRolesOfCurrentPerson`:

GetWorkRolesOfCurrentPerson
---------------------------

URL:
~~~~
    */api/v1/{format}/person/current/workroles*

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

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/persons/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/persons/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >

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
    */api/v1/{format}/persons/search*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/persons/search/first*

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

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/persons/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(KEY_ENUM_OBJECT_PAIR< `AddressTypeEnum <http://docs.ivis.se/en/latest/api/entities/AddressTypeEnum.html>`_ , `Address <http://docs.ivis.se/en/latest/api/entities/Address.html>`_ >)
        #. emails(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Email <http://docs.ivis.se/en/latest/api/entities/Email.html>`_ >)
        #. phones(KEY_ENUM_OBJECT_PAIR< `CommunicationTypeEnum <http://docs.ivis.se/en/latest/api/entities/CommunicationTypeEnum.html>`_ , `Phone <http://docs.ivis.se/en/latest/api/entities/Phone.html>`_ >)
        #. id(NUMBER)

