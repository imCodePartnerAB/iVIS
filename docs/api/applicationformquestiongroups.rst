ApplicationFormQuestionGroups
=============================

``(implementation of ApplicationFormQuestionGroup entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get applicationFormQuestionGroup`_
    * `Get applicationFormQuestionGroups`_
    * `Save applicationFormQuestionGroup`_
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
        #. id (Number)
        #. name (String)
        #. sort_order (Number)
        #. text (String)
        #. questions (Array)
        #. question_type (String)
        #. step (Array)

.. note::

    sort_order represent unique number according to sort purpose

    questions relate to ApplicationFormQuestion entity

    question_type - full class name which responds for question

    step relates to ApplicationFormStep entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "sort_order":1,
        "text":"Definition",
        "questions":[],
        "question_type":"com.imcode.ClassName",
        "step":{}
    }

.. _`Get applicationFormQuestionGroups`:

Get applicationFormQuestionGroups
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save applicationFormQuestionGroup`:

Save applicationFormQuestionGroup
-----------------------------------

URL:
~~~~
    */applicationformquestiongroups*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get applicationFormQuestionGroup`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update applicationFormQuestionGroups`:

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
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get applicationFormQuestionGroup`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete applicationFormQuestionGroups`:

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
    *Object*

