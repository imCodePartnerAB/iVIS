Activities
==========

``(implementation of Activity entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetAttachment`_
    * `SaveAll`_
    * `SaveAllAndReturnIds`_
    * `Search`_
    * `SearchFirst`_
    * `SetAttachment`_
    * `Update`_

.. _`Create`:

Create
------

URL:
~~~~
    */api/v1/{format}/activities*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/activities/{id}*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/activities*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/activities/{id}*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/activities*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`GetAttachment`:

GetAttachment
-------------

URL:
~~~~
    */api/v1/{format}/activities/attach/{id}*

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
        Starting downloading of file
.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/activities/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/activities/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/activities/search*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/activities/search/first*

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

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

.. _`SetAttachment`:

SetAttachment
-------------

URL:
~~~~
    */api/v1/{format}/activities/attach/{id}*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:


.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/activities/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. id(NUMBER)

