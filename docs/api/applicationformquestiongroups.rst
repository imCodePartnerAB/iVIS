ApplicationFormQuestionGroups
=============================

``(implementation of ApplicationFormQuestionGroup entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get applicationFormQuestionGroup`_
    * `Get applicationFormQuestionGroups`_
    * `Save applicationFormQuestionGroup`_
    * `Save applicationFormQuestionGroups`_
    * `Update applicationFormQuestionGroup`_
    * `Delete applicationFormQuestionGroup`_

.. _`Get applicationFormQuestionGroup`:

Get applicationFormQuestionGroup
--------------------------------

URL:
~~~~
    */applicationformquestiongroups/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties:*
        #. id(NUMBER)
        #. name(STRING)
        #. sort_order(NUMBER)
        #. text(STRING)
        #. questions(ARRAY)
           type of `ApplicationFormQuestion <http://docs.ivis.se/en/latest/api/applicationformquestion.html>`_
        #. question_type(STRING)
        #. step(OBJECT)
           type of `ApplicationFormStep <http://docs.ivis.se/en/latest/api/applicationformstep.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "sort_order" : 0,
      "text" : "",
      "questions" : [ ],
      "question_type" : "",
      "step" : {
        "id" : null,
        "name" : null,
        "sort_order" : null,
        "text" : null,
        "application_form" : null
      }
    }

.. _`Get applicationformquestiongroups`:

Get applicationformquestiongroups
---------------------------------

URL:
~~~~
    */applicationformquestiongroups*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. seealso::

    Array consists of objects from `Get applicationFormQuestionGroup`_ method

Save applicationFormQuestionGroup
---------------------------------

URL:
~~~~
    */applicationformquestiongroups*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestionGroup)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestionGroup)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save applicationformquestiongroups
----------------------------------

URL:
~~~~
    */applicationformquestiongroups*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormQuestionGroup)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormQuestionGroup)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update applicationFormQuestionGroup`:

Update applicationFormQuestionGroup
-----------------------------------

URL:
~~~~
    */applicationformquestiongroups/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestionGroup)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestionGroup)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete applicationFormQuestionGroup`:

Delete applicationFormQuestionGroup
-----------------------------------

URL:
~~~~
    */applicationformquestiongroups/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestionGroup)*

.. note::

    you receive deleted object

