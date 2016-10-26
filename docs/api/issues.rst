Issues
======

``(implementation of Issue entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get issue`_
    * `Get issues`_
    * `Save issue`_
    * `Save issues`_
    * `Update issue`_
    * `Delete issue`_
    * `Get issue list`_ by some criteria

.. _`Get issue`:

Get issue
---------

URL:
~~~~
    */issues/{id}*

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
      "reported_date" : 1477474353104,
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
      "modified_date" : 1477474353104,
      "activities" : [ ],
      "categories" : [ ],
      "priority" : {
        "id" : null,
        "name" : null
      },
      "pupils" : [ ]
    }

.. _`Get issues`:

Get issues
----------

URL:
~~~~
    */issues*

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

    Array consists of objects from `Get issue`_ method

Save issue
----------

URL:
~~~~
    */issues*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Issue)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Issue)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save issues
-----------

URL:
~~~~
    */issues*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Issue)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Issue)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update issue`:

Update issue
------------

URL:
~~~~
    */issues/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Issue)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Issue)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete issue`:

Delete issue
------------

URL:
~~~~
    */issues/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Issue)*

.. note::

    you receive deleted object

.. _`Get issues list`:

Get issues list
---------------

URL:
~~~~
    */issues*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    * *search_text*
    * *order_by*

.. note::
Now required for order_by only "title" and search criteria also title in Issue