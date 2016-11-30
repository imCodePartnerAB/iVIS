List of properties from Incident
================================

        #. id(NUMBER)
        #. title(STRING)
        #. description(STRING)
        #. reported_date(NUMBER)
        #. categories(ARRAY)
           type of `Category <http://docs.ivis.se/en/latest/api/category.html>`_
        #. pupils(ARRAY)
           type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_
        #. status(OBJECT)
           type of `Status <http://docs.ivis.se/en/latest/api/status.html>`_
        #. priority(OBJECT)
           type of `Priority <http://docs.ivis.se/en/latest/api/priority.html>`_
        #. issue(OBJECT)
           type of `Issue <http://docs.ivis.se/en/latest/api/issue.html>`_
        #. assigned_date(NUMBER)
        #. assigned_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. archived_date(NUMBER)
        #. archived_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. reported_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. modified_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. modified_date(NUMBER)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "title" : "",
      "description" : "",
      "reported_date" : 1477474352796,
      "categories" : [ ],
      "pupils" : [ ],
      "status" : {
        "id" : null,
        "name" : null
      },
      "priority" : {
        "id" : null,
        "name" : null
      },
      "issue" : {
        "id" : null,
        "title" : null,
        "description" : null,
        "status" : null,
        "responsible_person" : null,
        "authorized_persons" : [ ],
        "reported_date" : null,
        "reported_by" : null,
        "modified_by" : null,
        "modified_date" : null,
        "categories" : [ ],
        "priority" : null,
        "pupils" : [ ]
      },
      "assigned_date" : 1477474352798,
      "assigned_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "archived_date" : 1477474352798,
      "archived_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
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
      "modified_date" : 1477474352798
    }
