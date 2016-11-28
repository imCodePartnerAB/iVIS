Guardians
=========

``(implementation of Guardian entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
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
    */api/v1/{format}/guardians*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/guardians/{id}*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/guardians*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/guardians/{id}*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/guardians*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`GetByPersonalId`:

GetByPersonalId
---------------

URL:
~~~~
    */api/v1/{format}/guardians*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`GetFirstByPersonalId`:

GetFirstByPersonalId
--------------------

URL:
~~~~
    */api/v1/{format}/guardians*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/guardians/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/guardians/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/guardians/search*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/guardians/search/first*

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
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/guardians/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `Guardian <http://docs.ivis.se/en/latest/api/entities/Guardian.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. person(OBJECT< `Person <http://docs.ivis.se/en/latest/api/entities/Person.html>`_ >)
        #. pupils(ARRAY< `Pupil <http://docs.ivis.se/en/latest/api/entities/Pupil.html>`_ >)
        #. id(NUMBER)

