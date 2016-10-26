Persons
=======

``(implementation of Person entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get person`_
    * `Get persons`_
    * `Save person`_
    * `Save persons`_
    * `Update person`_
    * `Delete person`_
    * `Get person or persons by personal id`_

.. _`Get person`:

Get person
----------

URL:
~~~~
    */persons/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties:*
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

.. _`Get persons`:

Get persons
-----------

URL:
~~~~
    */persons*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. seealso::

    Array consists of objects from `Get person`_ method

Save person
-----------

URL:
~~~~
    */persons*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Person)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Person)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save persons
------------

URL:
~~~~
    */persons*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Person)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Person)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update person`:

Update person
-------------

URL:
~~~~
    */persons/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Person)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Person)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete person`:

Delete person
-------------

URL:
~~~~
    */persons/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Person)*

.. note::

    you receive deleted object

.. _`Get person or persons by personal id`:

Get person or persons by personal id
---------------------------------

URL:
~~~~
    */persons*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    and optional *first(BOOLEAN)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (Person)*


