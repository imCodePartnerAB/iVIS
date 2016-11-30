List of properties from Person
==============================

        #. id(NUMBER)
        #. personal_id(STRING)
        #. first_name(STRING)
        #. last_name(STRING)
        #. addresses(OBJECT)
            Object keys: "REGISTERED", "RESIDENTIAL", "BOARDER". See example of response.
        #. emails(OBJECT)
            Object keys: "MOBILE", "WORK", "HOME". See example of response.
        #. phones(OBJECT)
            Object keys: "MOBILE", "WORK", "HOME". See example of response.

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "personal_id" : "",
      "first_name" : "",
      "last_name" : "",
      "addresses" : {
            "REGISTERED": {
                "type": "REGISTERED",
                "postal_code": 1235,
                "municipality_code": "",
                "city": "",
                "street": "",
                "street2": "",
                "care_of": "",
                "address_type": "REGISTERED"
            }
        },
      "emails" : {
            "HOME": {
                    "type": "HOME",
                    "value": ""
            }
      },
      "phones" : {
            "HOME": {
                    "type": "HOME",
                    "value": ""
            }
      }
    }
