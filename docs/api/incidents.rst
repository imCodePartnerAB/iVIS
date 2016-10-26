Incidents
=========

``(implementation of Incident entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get incident`_
    * `Get incidents`_
    * `Save incident`_
    * `Save incidents`_
    * `Update incident`_
    * `Delete incident`_
    * `Get incidents list`_ by some criteria

.. _`Get incident`:

Get incident
------------

URL:
~~~~
    */incidents/{id}*

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
      "reported_date" : 1477474353092,
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
      "assigned_date" : 1477474353099,
      "assigned_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "archived_date" : 1477474353099,
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
      "modified_date" : 1477474353099
    }

.. _`Get incidents`:

Get incidents
-------------

URL:
~~~~
    */incidents*

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

    Array consists of objects from `Get incident`_ method

Save incident
-------------

URL:
~~~~
    */incidents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Incident)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Incident)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save incidents
--------------

URL:
~~~~
    */incidents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Incident)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Incident)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update incident`:

Update incident
---------------

URL:
~~~~
    */incidents/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Incident)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Incident)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete incident`:

Delete incident
---------------

URL:
~~~~
    */incidents/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Incident)*

.. note::

    you receive deleted object

.. _`Get incidents list`:

Get incidents list
------------------

URL:
~~~~
    */incidents*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    * *search_text*
    * *order_by*

.. note::

    Now required for order_by only "title" and search criteria also title in Incident

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. note::

    Array structure the same as `Get incidents`_



























