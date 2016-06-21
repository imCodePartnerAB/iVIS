Incidents
=========

``(implementation of Incident entity)``

Provides following method for `API <index.html>`_  calls:

    * `Get incident`_
    * `Get incidents`_
    * `Get incidents list`_ (by some criteria)
    * `Save incident`_
    * `Update incident`_
    * `Delete incident`_

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
        #. id (Number)
        #. title (String)
        #. description (String)
        #. categories (Array)
        #. priority (Object)
        #. pupils (Array)
        #. status (Object)
        #. reported_date (Number),
        #. reported_by (Array)
        #. reported_date (Number)
        #. issue (Object)
        #. assigned_date (Number)
        #. assigned_by (Object)
        #. archived_date (Number)
        #. archived_by (Object)
        #. modified_date (Number)
        #. modified_by (Object)

.. note::

    *_date represent as number of seconds since 1 Jan 1970 00:00:00 (UTC)

    *_by reference in implementation to Person entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
       "id":1,
       "title":"Test",
       "description":"Test",
       "categories":[  ],
       "pupils":[  ],
       "status":{  },
       "priority":{  },
       "issue":{  },
       "reported_date":1462441906000,
       "assigned_date":1466147548000,
       "assigned_by":{  },
       "archived_date":1466147548000,
       "archived_by":{  },
       "reported_by":{  },
       "modified_by":{  },
       "modified_date":1462441906000
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    }

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

.. _`Save incident`:

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
    *Object*

    *With properties:*
        #. title (String)
        #. description (String)
        #. categories (Array)
        #. pupils (Array)
        #. priority (Object)

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill properties*
        #. id (Number)
        #. reported_date (Number)
        #. reported_by (Object)

.. note::
    You retrieve whole object just like in `Get incident`_

Example of request:
~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "title" : "Test",
        "description" : "Test",
        "categories" : [ {id : 1}, {id : 2}, ... {id : n} ],
        "priority" : { id : 1 },
        "pupils" : [ {id : 1}, {id : 2}, ... {id : n} ]
    }

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
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get incident`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

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
    *Object*





























