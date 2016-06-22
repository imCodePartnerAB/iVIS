ApplicationFormQuestions
========================

``(implementation of ApplicationFormQuestion entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get applicationFormQuestion`_
    * `Get applicationFormQuestions`_
    * `Save applicationFormQuestion`_
    * `Update applicationFormQuestion`_
    * `Delete applicationFormQuestion`_

.. _`Get applicationFormQuestion`:

Get applicationFormQuestion
---------------------------

URL:
~~~~
    */applicationformquestions/{id}*

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
        #. text (String)
        #. value (String)
        #. values (Array)
        #. variants (Array)
        #. sort_order (Number)
        #. multi_values (Boolean)
        #. multi_variants (Boolean)
        #. question_type (String)
        #. question_group (Object)

.. note::

    values, variants - Arrays of String

    questions relate to ApplicationFormQuestion entity

    question_type - full class name which responds for question

    question_group relate to ApplicationFormQuestionGroup entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "text":"Definition",
        "value":"Value",
        "values":[],
        "variants":[],
        "sort_order":1,
        "multi_values":false,
        "multi_variants":false,
        "question_type":"com.imcode.ClassName",
        "question_group":{}
    }

.. _`Get applicationFormQuestions`:

Get applicationFormQuestions
----------------------------

URL:
~~~~
    */applicationformquestions*

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
    Array consists of objects from `Get applicationFormQuestion`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save applicationFormQuestion`:

Save applicationFormQuestion
----------------------------

URL:
~~~~
    */applicationformquestions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get applicationFormQuestion`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update applicationFormQuestions`:

Update applicationFormQuestion
------------------------------

URL:
~~~~
    */applicationformquestions/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get applicationFormQuestion`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete applicationFormQuestions`:

Delete applicationFormQuestion
------------------------------

URL:
~~~~
    */applicationformquestions/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

