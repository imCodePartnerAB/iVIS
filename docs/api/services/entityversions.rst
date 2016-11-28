EntityVersions
==============

``(implementation of EntityVersion entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetByEntityId`_
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
    */api/v1/{format}/entityversions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `EntityVersion <http://docs.ivis.se/en/latest/api/entities/EntityVersion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/entityversions/{id}*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/entityversions*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/entityversions/{id}*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/entityversions*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`GetByEntityId`:

GetByEntityId
-------------

URL:
~~~~
    */api/v1/{format}/entityversions*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: entityId

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/entityversions/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `EntityVersion <http://docs.ivis.se/en/latest/api/entities/EntityVersion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/entityversions/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `EntityVersion <http://docs.ivis.se/en/latest/api/entities/EntityVersion.html>`_ >

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
    */api/v1/{format}/entityversions/search*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/entityversions/search/first*

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

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/entityversions/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `EntityVersion <http://docs.ivis.se/en/latest/api/entities/EntityVersion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class(STRING<ENTITY_CLASS_NAME>)
        #. entity_id(NUMBER)
        #. entity(NUMBER)
        #. id(NUMBER)

