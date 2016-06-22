Activities
==========

``(implementation of Activity entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get activity`_
    * `Get activities`_
    * `Save activity`_
    * `Attach file to activity`_
    * `Download attached file`_

.. _`Get activity`:

Get activity
------------

URL:
~~~~
    */activities/{id}*

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
        #. description (String)
        #. reported_date (Number)
        #. reported_by (Object)
        #. file_name (String)
        #. issue (Object)

.. note::

    reported_by links with Person entity

    reported_date represent as number of seconds since 1 Jan 1970 00:00:00 (UTC)

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "description":"Test",
        "reported_date":1465828993000,
        "reported_by":{},
        "issue":{},
        "file_name":"file.pdf",
    }

.. _`Get activities`:

Get activities
--------------

URL:
~~~~
    */activities*

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
    Array consists of objects from `Get activities`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save activity`:

Save activity
-------------

URL:
~~~~
    */activities*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties:*
        #. description (String)
        #. issue (Object)

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill properties*
        #. id (Number)
        #. reported_date (Number)
        #. reported_by (Object)

.. note::
    You retrieve whole object just like in `Get activity`_

Example of request:
~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "description" : "Test",
        "issue" : { id : 1 }
    }

.. _`Attach file to activity`:

Attach file to activity
-----------------------

URL:
~~~~
    */activities/attach/{id}*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *One file*

    *With property* **name = "file"**

.. note::
    Max file size can not be more than 20 Mb

    When you upload file -> on server fill file_name property with appropriate activity

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *String*

    *Description:* file name of attached file

.. _`Download attached file`:

URL:
~~~~
    */activities/attach/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *File*

    *Description:* starting download a file





