List of properties from Guardian
================================

        #. id(NUMBER)
        #. person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. pupils(ARRAY)
           type of `Pupil <http://docs.ivis.se/en/latest/api/pupil.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "person" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "pupils" : [ ]
    }
