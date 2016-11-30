List of properties from Issue
=============================

        #. id(NUMBER)
        #. title(STRING)
        #. description(STRING)
        #. status(OBJECT)
           type of `Status <http://docs.ivis.se/en/latest/api/status.html>`_
        #. responsible_person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. authorized_persons(ARRAY)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. incidents(ARRAY)
           type of `Incident <http://docs.ivis.se/en/latest/api/incident.html>`_
        #. reported_date(NUMBER)
        #. reported_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. modified_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. modified_date(NUMBER)
        #. activities(ARRAY)
           type of `Activity <http://docs.ivis.se/en/latest/api/activity.html>`_
        #. categories(ARRAY)
           type of `Category <http://docs.ivis.se/en/latest/api/category.html>`_
        #. priority(OBJECT)
           type of `Priority <http://docs.ivis.se/en/latest/api/priority.html>`_
        #. pupils(ARRAY)
           type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "title" : "",
      "description" : "",
      "status" : {
        "id" : null,
        "name" : null
      },
      "responsible_person" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "authorized_persons" : [ ],
      "incidents" : [ ],
      "reported_date" : 1477473026987,
      "reported_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "modified_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "modified_date" : 1477473026987,
      "activities" : [ ],
      "categories" : [ ],
      "priority" : {
        "id" : null,
        "name" : null
      },
      "pupils" : [ ]
    }
