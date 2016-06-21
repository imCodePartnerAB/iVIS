Pupils
======

``(implementation of Pupil entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get pupil`_
    * `Get pupils`_

.. _`Get pupil`:

Get pupil
---------

URL:
~~~~
    */pupils/{id}*

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
    #. person (Object)
    #. school (Object)
    #. guardians (Array)
    Other properties aren't necessary.

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "person":{},
        "contactPerson":{},
        "classPlacementFrom":{},
        "classPlacementTo":{},
        "schoolClass":[],
        "school":{},
        "academicYear":{},
        "guardians":[],
        "truancies":[],
        "afterSchoolCenterSection":{},
        "schoolCenterSchema":[]
    }

.. _`Get pupils`:

Get pupils
----------

URL:
~~~~
    */pupils*

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
    Array consists of objects from `Get pupil`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

