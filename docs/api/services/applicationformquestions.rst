ApplicationFormQuestions
========================

``(implementation of ApplicationFormQuestion entity)``

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
    */api/v1/{format}/applicationformquestions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/{id}*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/applicationformquestions*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/applicationformquestions/{id}*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/applicationformquestions*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. text(STRING)
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/search*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/search/first*

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
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/applicationformquestions/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestion.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY<STRING>)
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY<STRING>)
        #. question_type(STRING)
        #. question_group(OBJECT< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

