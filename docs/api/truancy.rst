List of properties from Truancy
===============================

        #. id(NUMBER)
        #. pupil(OBJECT)
           type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_
        #. start_date(NUMBER)
        #. end_date(NUMBER)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "pupil" : {
        "id" : null,
        "person" : {
          "id" : null,
          "personal_id" : null,
          "first_name" : null,
          "last_name" : null,
          "addresses" : { },
          "emails" : { },
          "phones" : { }
        },
        "contact_person" : null,
        "class_placement_from" : null,
        "class_placement_to" : null,
        "school_class" : null,
        "school" : null,
        "academic_year" : null,
        "guardians" : [ ],
        "after_school_center_section" : null,
        "school_center_schema" : [ ]
      },
      "start_date" : 1477474352801,
      "end_date" : 1477474352801
    }
