ApplicationForms
================

``(implementation of ApplicationForm entity)``

Provides following method for `API <http://docs.ivis.se/en/latest/api/index.html>`_ calls:

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
    */api/v1/{format}/applicationforms*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/applicationforms/{id}*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/applicationforms*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/applicationforms/{id}*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/applicationforms*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/applicationforms/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/applicationforms/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >

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
    */api/v1/{format}/applicationforms/search*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/applicationforms/search/first*

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

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/applicationforms/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. version(NUMBER)
        #. steps(ARRAY< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. applications(ARRAY< `Application <http://docs.ivis.se/en/latest/api/entities/Application.html>`_ >)
        #. name(STRING)
        #. id(NUMBER)

