Issues
======

``(implementation of Issue entity)``

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
    */api/v1/{format}/issues*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/issues/{id}*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/issues*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`FindByCriteria`:

FindByCriteria
--------------

URL:
~~~~
    */api/v1/{format}/issues*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/issues/{id}*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/issues*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/issues/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/issues/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/issues/search*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/issues/search/first*

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
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/issues/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Issue <http://docs.ivis.se/en/latest/api/entities/Issue.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. title(STRING)
        #. description(STRING)
        #. status(OBJECT< `Status <http://docs.ivis.se/en/latest/api/entities/Status.html>`_ >)
        #. responsible_person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. authorized_persons(ARRAY< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. incidents(ARRAY< `Incident <http://docs.ivis.se/en/latest/api/entities/Incident.html>`_ >)
        #. reported_date(NUMBER(Date representation wrapped))
        #. reported_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_by(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. modified_date(NUMBER(Date representation wrapped))
        #. activities(ARRAY< `Activity <http://docs.ivis.se/en/latest/api/entities/Activity.html>`_ >)
        #. categories(ARRAY< `Category <http://docs.ivis.se/en/latest/api/entities/Category.html>`_ >)
        #. priority(OBJECT< `Priority <http://docs.ivis.se/en/latest/api/entities/Priority.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

