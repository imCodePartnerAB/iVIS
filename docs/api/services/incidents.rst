Incidents
=========

``(implementation of Incident entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `FindByCriteria`_
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
    */api/v1/{format}/incidents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/incidents/{id}*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/incidents*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`FindByCriteria`:

FindByCriteria
--------------

URL:
~~~~
    */api/v1/{format}/incidents*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/incidents/{id}*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/incidents*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/incidents/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/incidents/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/incidents/search*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/incidents/search/first*

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

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/incidents/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER(Date representation wrapped))
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. issue(OBJECT< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >)
        #. assigned_date(NUMBER(Date representation wrapped))
        #. assigned_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. archived_date(NUMBER(Date representation wrapped))
        #. archived_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. id(NUMBER)

