ApplicationFormSteps
====================

``(implementation of ApplicationFormStep entity)``

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
    */api/v1/{format}/applicationformsteps*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/applicationformsteps/{id}*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/applicationformsteps*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/applicationformsteps/{id}*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/applicationformsteps*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/applicationformsteps/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. text(STRING)
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/applicationformsteps/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >

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
    */api/v1/{format}/applicationformsteps/search*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/applicationformsteps/search/first*

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
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/applicationformsteps/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `ApplicationFormStep <http://docs.ivis.se/en/latest/api/entities/ApplicationFormStep.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. text(STRING)
        #. question_groups(ARRAY< `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/entities/ApplicationFormQuestionGroup.html>`_ >)
        #. application_form(OBJECT< `ApplicationForm <http://docs.ivis.se/en/latest/api/entities/ApplicationForm.html>`_ >)
        #. sort_order(NUMBER)
        #. name(STRING)
        #. id(NUMBER)

