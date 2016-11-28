Applications
============

``(implementation of Application entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
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
    */api/v1/{format}/applications*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/applications/{id}*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/applications*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/applications/{id}*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/applications*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/applications/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/applications/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/applications/search*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/applications/search/first*

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

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/applications/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. submitted_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. regarding_user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. registration_number(NUMBER)
        #. decision(OBJECT< `Decision <http://docs.ivis.se/en/latest/api/entities/Decision.html>`_ >)
        #. handled_user(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. create_date(NUMBER(Date representation wrapped))
        #. update_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

