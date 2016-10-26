AfterSchoolCenterSections
=========================

``(implementation of AfterSchoolCenterSection entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get afterSchoolCenterSection`_
    * `Get afterSchoolCenterSections`_
    * `Save afterSchoolCenterSection`_
    * `Save afterSchoolCenterSections`_
    * `Update afterSchoolCenterSection`_
    * `Delete afterSchoolCenterSection`_
    * `Get afterSchoolCenterSection or afterSchoolCenterSections by personal id`_
    * `Get afterSchoolCenterSection or afterSchoolCenterSections by name`_

.. _`Get afterSchoolCenterSection`:

Get afterSchoolCenterSection
----------------------------

URL:
~~~~
    */afterschoolcentersections/{id}*

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
        #. name(STRING)
        #. school(OBJECT)
           type of `School <http://docs.ivis.se/en/latest/api/school.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "school" : {
        "id" : null,
        "name" : null,
        "school_id" : null
      }
    }

.. _`Get afterschoolcentersections`:

Get afterschoolcentersections
-----------------------------

URL:
~~~~
    */afterschoolcentersections*

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
    
Array consists of objects from `Get afterSchoolCenterSection`_ method

Save afterSchoolCenterSection
-----------------------------

URL:
~~~~
    */afterschoolcentersections*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(AfterSchoolCenterSection)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AfterSchoolCenterSection)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save afterschoolcentersections
------------------------------

URL:
~~~~
    */afterschoolcentersections*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(AfterSchoolCenterSection)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(AfterSchoolCenterSection)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update afterSchoolCenterSection`:

Update afterSchoolCenterSection
-------------------------------

URL:
~~~~
    */afterschoolcentersections/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(AfterSchoolCenterSection)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AfterSchoolCenterSection)*

.. note::
    
property will be updated, if you don't want update property it need set null

.. _`Delete afterSchoolCenterSection`:

Delete afterSchoolCenterSection
-------------------------------

URL:
~~~~
    */afterschoolcentersections/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(AfterSchoolCenterSection)*

.. note::
    you receive deleted object

.. _`Get afterSchoolCenterSection or afterSchoolCenterSections by personal id`:

Get afterSchoolCenterSection or afterSchoolCenterSections by personal id
---------------------------------------------------------------------

URL:
~~~~
    */afterschoolcentersections

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (AfterSchoolCenterSection)*

.. _`Get afterSchoolCenterSection or afterSchoolCenterSections by name`:

Get afterSchoolCenterSection or afterSchoolCenterSections by name
-----------------------------------------------------------------

URL:
~~~~
    */afterschoolcentersections

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (AfterSchoolCenterSection)*

