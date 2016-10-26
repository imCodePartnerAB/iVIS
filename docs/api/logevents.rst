LogEvents
=========

``(implementation of LogEvent entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get logEvent`_
    * `Get logEvents`_
    * `Save logEvent`_
    * `Save logEvents`_
    * `Update logEvent`_
    * `Delete logEvent`_
    * `Get logEvent or logEvents by personal id`_
    * `Get logEvent or logEvents by name`_

.. _`Get logEvent`:

Get logEvent
------------

URL:
~~~~
    */logevents/{id}*

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
        #. timestamp(NUMBER)
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(NULL)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT)
           type of `User <http://docs.ivis.se/en/latest/api/user.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "timestamp" : 1477471556931,
      "entity_class_name" : "",
      "entity_id" : 0,
      "action" : null,
      "field_name" : "",
      "previous_value" : "",
      "new_value" : "",
      "user" : {
        "id" : null,
        "name" : null,
        "person" : null,
        "roles" : [ ],
        "saml2_id" : null
      }
    }

.. _`Get logevents`:

Get logevents
-------------

URL:
~~~~
    */logevents*

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
    
Array consists of objects from `Get logEvent`_ method

Save logEvent
-------------

URL:
~~~~
    */logevents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(LogEvent)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(LogEvent)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save logevents
--------------

URL:
~~~~
    */logevents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(LogEvent)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(LogEvent)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update logEvent`:

Update logEvent
---------------

URL:
~~~~
    */logevents/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(LogEvent)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(LogEvent)*

.. note::
    
property will be updated, if you don't want update property it need set null

.. _`Delete logEvent`:

Delete logEvent
---------------

URL:
~~~~
    */logevents/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(LogEvent)*

.. note::
    you receive deleted object

.. _`Get logEvent or logEvents by personal id`:

Get logEvent or logEvents by personal id
-------------------------------------

URL:
~~~~
    */logevents

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *personalId(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (LogEvent)*

.. _`Get logEvent or logEvents by name`:

Get logEvent or logEvents by name
---------------------------------

URL:
~~~~
    */logevents

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *name(STRING)*
    *first(BOOLEAN)* - optional

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *ARRAY or OBJECT (LogEvent)*

