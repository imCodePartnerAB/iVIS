Activities
==========

``(implementation of Activity entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get activity`_
    * `Get activities`_
    * `Save activity`_
    * `Save activities`_
    * `Update activity`_
    * `Delete activity`_
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
        #. id(NUMBER)
        #. description(STRING)
        #. file_name(STRING)
        #. issue(OBJECT)
           type of `Issue <http://docs.ivis.se/en/latest/api/issue.html>`_
        #. reported_date(NUMBER)
        #. reported_by(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "description" : "",
      "file_name" : "",
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
      "reported_date" : 1477473027056,
      "reported_by" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      }
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

    Array consists of objects from `Get activity`_ method

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
    *OBJECT(Activity)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Activity)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save activities
---------------

URL:
~~~~
    */activities*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Activity)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Activity)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update activity`:

Update activity
---------------

URL:
~~~~
    */activities/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Activity)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Activity)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete activity`:

Delete activity
---------------

URL:
~~~~
    */activities/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Activity)*

.. note::

    you receive deleted object

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

    When you upload file -> on server fill file_name property in appropriate activity

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





