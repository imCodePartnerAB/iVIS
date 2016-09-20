Persons
=======

``(implementation of Person entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get person`_
    * `Get persons`_
    * `Get persons list`_ (by some criteria)

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
        #. id (Number)
        #. first_name (String)
        #. last_name (String)
        #. personal_id (String)
        #. addresses (Object)
        #. emails (Object)
        #. phones (Object)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id": 1,
        "addresses": {},
        "emails": {},
        "phones": {},
        "personal_id": "number-1111",
        "first_name": "Test",
        "last_name": "Test"
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Get persons list`:

Get persons list
----------------

URL:
~~~~
    */persons*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    * *search_text*
    * *order_by*

.. note::
    Search in last name, or first name

    order_by can be: "last_name", "first_name"

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. note::
Array structure the same as `Get persons`_
