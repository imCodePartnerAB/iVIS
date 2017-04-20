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
    * `GetFirstByPersonalId`_
    * `GetPersonOfCurrentUser`_
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
    */api/v1/{format}/persons/{id}*

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

.. _`GetPersonOfCurrentUser`:

GetPersonOfCurrentUser
----------------------

URL:
~~~~
    */api/v1/{format}/persons/ofcurrentuser*

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

