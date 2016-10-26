Guardians
=========

``(implementation of Guardian entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get guardian`_
    * `Get guardians`_
    * `Save guardian`_
    * `Save guardians`_
    * `Update guardian`_
    * `Delete guardian`_
    * `Get guardian or guardians by personal id`_
    * `Get guardian or guardians by name`_

.. _`Get guardian`:

Get guardian
------------

URL:
~~~~
    */guardians/{id}*

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
        #. person(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_
        #. pupils(ARRAY)
           type of `pupils <http://docs.ivis.se/en/latest/api/pupil.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "person" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      },
      "pupils" : [ ]
    }

.. _`Get guardians`:

Get guardians
-------------

URL:
~~~~
    */guardians*

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
    
Array consists of objects from `Get guardian`_ method

Save guardian
-------------

URL:
~~~~
    */guardians*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Guardian)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Guardian)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save guardians
--------------

URL:
~~~~
    */guardians*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Guardian)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Guardian)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update guardian`:

Update guardian
---------------

URL:
~~~~
    */guardians/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(Guardian)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Guardian)*

.. note::
    
property will be updated, if you don't want update property it need set null

.. _`Delete guardian`:

Delete guardian
---------------

URL:
~~~~
    */guardians/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Guardian)*

.. note::
    you receive deleted object

.. _`Get guardian or guardians by personal id`:

Get guardian or guardians by personal id
-------------------------------------

URL:
~~~~
    */guardians

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (Guardian)*

.. _`Get guardian or guardians by name`:

Get guardian or guardians by name
---------------------------------

URL:
~~~~
    */guardians

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (Guardian)*

