ApplicationFormQuestionGroups
=============================

``(implementation of ApplicationFormQuestionGroup entity)``

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
    */api/v1/{format}/applicationformquestiongroups*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/{id}*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/{id}*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/search*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/search/first*

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

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/applicationformquestiongroups/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. questions(ARRAY< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >)
        #. question_type(STRING)
        #. step(OBJECT< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

