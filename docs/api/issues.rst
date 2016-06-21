Issues
======

``(implementation of Issue entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get issue`_
    * `Get issues`_
    * `Get issues list`_ (by some criteria)
    * `Save issue`_
    * `Update issue`_
    * `Delete issue`_

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
        #. id (Number)
        #. title (String)
        #. description (String)
        #. categories (Array)
        #. status (Object)
        #. responsible_person (Object)
        #. authorized_persons (Array)
        #. incidents (Array)
        #. activities (Array)
        #. priority (Object)
        #. pupils (Object)

.. note::

    responsible_person and authorized_persons link with Person entity

    pupils property links with Pupil entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "title":"Test",
        "description":"Test",
        "status":{},
        "incidents":[],
        "activities":[],
        "categories":[],
        "priority":{},
        "pupils":[],
        "responsible_person":{},
        "authorized_persons":[],
        "reported_date":1465828993000,
        "reported_by":{},
        "modified_by":{},
        "modified_date":1465828993000
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    }

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

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

.. note::
    Array structure the same as `Get issues`_

.. _`Save incident`:

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
    *Object*

    *With properties:*
        #. title (String)
        #. description (String)
        #. status (Object)
        #. responsible_person (Object)
        #. authorized_persons (Array)
        #. activities (Array)
        #. categories (Array)
        #. pupils (Array)
        #. priority (Object)
        #. incidents (Array)

.. note::
    You can update properties of exists incidents by appropriate property

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill properties*
        #. id (Number)
        #. reported_date (Number)
        #. reported_by (Object)

.. note::
    You retrieve whole object just like in `Get issue`_

Example of request:
~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "title" : "Test",
        "description" : "Test",
        "status" : { id : 1 },
        "responsible_person" : { id : 1 },
        "authorized_persons" : [ {id : 1}, {id : 2}, ... {id : n} ],
        "activities" : [ {id : 1}, {id : 2}, ... {id : n} ],
        "categories" : [ {id : 1}, {id : 2}, ... {id : n} ],
        "priority" : { id : 1 },
        "pupils" : [ {id : 1}, {id : 2}, ... {id : n} ],
        "incidents" : [
                        {
                            "id" : 1,
                            "assigned_date" : 1465828993000,
                            "assigned_by" : { id : 1 },
                            "archived_date" : 1465828993000,
                            "archived_by" : { id : 1 }
                        },
                        ...
                      ]
    }

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
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get issue`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

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
    *Object*






