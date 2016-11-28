Users
=====

``(implementation of User entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetByPersonalId`_
    * `GetCurrentUser`_
    * `GetFirstByPersonalId`_
    * `GetLoggedInUser`_
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
    */api/v1/{format}/users*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/users/{id}*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/users*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/users/{id}*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/users*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetByPersonalId`:

GetByPersonalId
---------------

URL:
~~~~
    */api/v1/{format}/users*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetCurrentUser`:

GetCurrentUser
--------------

URL:
~~~~
    */api/v1/{format}/users/current*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetFirstByPersonalId`:

GetFirstByPersonalId
--------------------

URL:
~~~~
    */api/v1/{format}/users*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetLoggedInUser`:

GetLoggedInUser
---------------

URL:
~~~~
    */api/v1/{format}/users/loggedin*

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



.. note::
   This method return only 3 properties: id, person, roles.

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/users/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/users/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/users/search*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/users/search/first*

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
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/users/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. roles(ARRAY< `Role <http://docs.ivis.se/en/latest/api/entities/Role.html>`_ >)
        #. saml2_id(STRING)
        #. name(STRING)
        #. id(NUMBER)

