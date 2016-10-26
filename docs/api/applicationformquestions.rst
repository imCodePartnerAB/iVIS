ApplicationFormQuestions
========================

``(implementation of ApplicationFormQuestion entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get applicationFormQuestion`_
    * `Get applicationFormQuestions`_
    * `Save applicationFormQuestion`_
    * `Save applicationFormQuestions`_
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
        #. id(NUMBER)
        #. name(STRING)
        #. sort_order(NUMBER)
        #. text(STRING)
        #. value(STRING)
        #. multi_values(BOOLEAN)
        #. values(ARRAY)
           type of STRING
        #. multi_variants(BOOLEAN)
        #. variants(ARRAY)
           type of STRING
        #. question_type(STRING)
        #. question_group(OBJECT)
           type of `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/applicationformquestiongroup.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "sort_order" : 0,
      "text" : "",
      "value" : "",
      "multi_values" : false,
      "values" : [ ],
      "multi_variants" : false,
      "variants" : [ ],
      "question_type" : "",
      "question_group" : {
        "id" : null,
        "name" : null,
        "sort_order" : null,
        "text" : null,
        "question_type" : null,
        "step" : null
      }
    }

.. _`Get applicationformquestions`:

Get applicationformquestions
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
    *OBJECT(ApplicationFormQuestion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestion)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save applicationformquestions
-----------------------------

URL:
~~~~
    */applicationformquestions*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormQuestion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormQuestion)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update applicationFormQuestion`:

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
    *OBJECT(ApplicationFormQuestion)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormQuestion)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete applicationFormQuestion`:

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
    *OBJECT(ApplicationFormQuestion)*

.. note::

    you receive deleted object

.. _`Get applicationFormQuestion or applicationFormQuestions by name`:

