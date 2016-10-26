List of properties from Application
===================================

        #. id(NUMBER)
        #. create_date(NUMBER)
        #. update_date(NUMBER)
        #. application_form(OBJECT)
           type of `ApplicationForm <http://docs.ivis.se/en/latest/api/applicationform.html>`_
        #. submitted_user(OBJECT)
           type of `User <http://docs.ivis.se/en/latest/api/user.html>`_
        #. regarding_user(OBJECT)
           type of `User <http://docs.ivis.se/en/latest/api/user.html>`_
        #. registration_number(NUMBER)
        #. decision(OBJECT)
            type of `Decision <http://docs.ivis.se/en/latest/api/decision.html>`_
        #. handled_user(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "create_date" : 1477474353027,
      "update_date" : 1477474353027,
      "application_form" : {
        "id" : null,
        "name" : null,
        "version" : null,
        "steps" : [ ]
      },
      "submitted_user" : {
        "id" : null,
        "name" : null,
        "person" : null,
        "roles" : [ ],
        "saml2_id" : null
      },
      "regarding_user" : {
        "id" : null,
        "name" : null,
        "person" : null,
        "roles" : [ ],
        "saml2_id" : null
      },
      "registration_number" : 0,
      "decision" : null,
      "handled_user" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      }
    }
