List of properties from ApplicationFormQuestionGroup
====================================================

        #. id(NUMBER)
        #. name(STRING)
        #. sort_order(NUMBER)
        #. text(STRING)
        #. questions(ARRAY)
           type of STRING
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
