List of properties from ApplicationFormStep
===========================================

        #. id(NUMBER)
        #. name(STRING)
        #. sort_order(NUMBER)
        #. text(STRING)
        #. question_groups(ARRAY)
           type of `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/applicationformquestiongroup.html>`_
        #. application_form(OBJECT)
           type of `ApplicationForm <http://docs.ivis.se/en/latest/api/applicationform.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "sort_order" : 0,
      "text" : "",
      "question_groups" : [ ],
      "application_form" : {
        "id" : null,
        "name" : null,
        "version" : null
      }
    }
