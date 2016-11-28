LogEvents
=========

``(implementation of LogEvent entity)``

Provides following method for `API <index.html>`_ calls:

    * `Create`_
    * `Delete`_
    * `DeleteByIds`_
    * `Get`_
    * `GetAll`_
    * `GetByEntityId`_
    * `SaveAll`_
    * `SaveAllAndReturnIds`_
    * `Search`_
    * `SearchFirst`_
    * `Update`_

.. _`Create`:

Create
------

URL:
~~~~
    */api/v1/{format}/logevents*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `LogEvent <http://docs.ivis.se/en/latest/api/entities/LogEvent.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`Delete`:

Delete
------

URL:
~~~~
    */api/v1/{format}/logevents/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`DeleteByIds`:

DeleteByIds
-----------

URL:
~~~~
    */api/v1/{format}/logevents*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: ids

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`Get`:

Get
---

URL:
~~~~
    */api/v1/{format}/logevents/{id}*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`GetAll`:

GetAll
------

URL:
~~~~
    */api/v1/{format}/logevents*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`GetByEntityId`:

GetByEntityId
-------------

URL:
~~~~
    */api/v1/{format}/logevents*

Method:
~~~~~~~
    *GET*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: entityId

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`SaveAll`:

SaveAll
-------

URL:
~~~~
    */api/v1/{format}/logevents/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `LogEvent <http://docs.ivis.se/en/latest/api/entities/LogEvent.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`SaveAllAndReturnIds`:

SaveAllAndReturnIds
-------------------

URL:
~~~~
    */api/v1/{format}/logevents/saveall*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Url parameters: full

    Array< `LogEvent <http://docs.ivis.se/en/latest/api/entities/LogEvent.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:


.. _`Search`:

Search
------

URL:
~~~~
    */api/v1/{format}/logevents/search*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `SearchCriteries$SearchCriteriaResult <http://docs.ivis.se/en/latest/api/entities/SearchCriteries$SearchCriteriaResult.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`SearchFirst`:

SearchFirst
-----------

URL:
~~~~
    */api/v1/{format}/logevents/search/first*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Array< `SearchCriteries$SearchCriteriaResult <http://docs.ivis.se/en/latest/api/entities/SearchCriteries$SearchCriteriaResult.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

.. _`Update`:

Update
------

URL:
~~~~
    */api/v1/{format}/logevents/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    Object< `LogEvent <http://docs.ivis.se/en/latest/api/entities/LogEvent.html>`_ >

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    Description:

        #. timestamp(NUMBER(Date representation wrapped))
        #. entity_class_name(STRING)
        #. entity_id(NUMBER)
        #. action(OBJECT< `LogEvent$Action <http://docs.ivis.se/en/latest/api/entities/LogEvent$Action.html>`_ >)
        #. field_name(STRING)
        #. previous_value(STRING)
        #. new_value(STRING)
        #. user(OBJECT< `User <http://docs.ivis.se/en/latest/api/entities/User.html>`_ >)
        #. id(NUMBER)

