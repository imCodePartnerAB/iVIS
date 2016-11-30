List of properties from User
============================

        #. id(NUMBER)
        #. name(STRING)
        #. person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. roles(ARRAY)
           type of `Role <http://docs.ivis.se/en/latest/api/role.html>`_
        #. saml2_id(STRING)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "person" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "roles" : [ ],
      "saml2_id" : ""
    }
