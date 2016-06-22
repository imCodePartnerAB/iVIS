Schools
=======

``(implementation of School entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get school`_
    * `Get schools`_
    * `Save school`_
    * `Update school`_
    * `Delete school`_

.. _`Get school`:

Get school
----------

URL:
~~~~
    */schools/{id}*

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
        #. name (String)
        #. school_id (String)
        #. school_classes (Array)
        #. after_school_center_sections (Array)

.. note::

    school_classes relates to SchoolClass entity
    
    after_school_center_sections relates to afterSchoolCenterSections entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "school_id":"1111-id",
        "school_classes":[],
        "after_school_center_sections":[]
    }

.. _`Get schools`:

Get schools
-----------

URL:
~~~~
    */schools*

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
Array consists of objects from `Get school`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save school`:

Save school
-----------

URL:
~~~~
    */schools*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get school`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update school`:

Update school
-------------

URL:
~~~~
    */schools/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get school`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete  school`:

Delete school
-------------

URL:
~~~~
    */schools/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

