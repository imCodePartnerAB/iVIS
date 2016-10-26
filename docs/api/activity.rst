List of properties from Activity
================================

        #. id(NUMBER)
        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT)
           type of `Issue <http://docs.ivis.se/en/latest/api/issue.html>`_
        #. reported_date(NUMBER)
        #. reported_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "description" : "",
      "file_name" : "",
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
      "reported_date" : 1477474352776,
      "reported_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      }
    }
