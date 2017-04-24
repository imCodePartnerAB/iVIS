Schools
=======

``(implementation of School entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetByName`_
    * `GetFirstByName`_
    * `GetPersonsBySchool`_
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
    */api/v1/{format}/school*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/school/{id}*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/schools*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/school/{id}*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/schools*

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

.. _`GetByName`:

GetByName
---------

URL:
~~~~
    */api/v1/{format}/schools*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetFirstByName`:

GetFirstByName
--------------

URL:
~~~~
    */api/v1/{format}/school*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetPersonsBySchool`:

GetPersonsBySchool
------------------

URL:
~~~~
    */api/v1/{format}/school/{id}/persons*

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

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/schools/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >

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

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/schools/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `School <http://docs.ivis.se/en/latest/api/entities/School.html>`_ >

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
    */api/v1/{format}/schools/search*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/school/search*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/school/{id}*

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

        #. school_id(STRING)
        #. services(ARRAY< `ServiceTypeEnum <http://docs.ivis.se/en/latest/api/entities/ServiceTypeEnum.html>`_ >)
        #. school_classes(ARRAY< `SchoolClass <http://docs.ivis.se/en/latest/api/entities/SchoolClass.html>`_ >)
        #. after_school_center_sections(ARRAY< `AfterSchoolCenterSection <http://docs.ivis.se/en/latest/api/entities/AfterSchoolCenterSection.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)
