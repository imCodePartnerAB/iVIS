List of properties from ApplicationFormQuestion
===============================================

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
