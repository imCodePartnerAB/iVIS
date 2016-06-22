Applications
============

``(implementation of Application entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get application`_
    * `Get applications`_
    * `Save application`_
    * `Update application`_
    * `Delete application`_

.. _`Get application`:

Get application
---------------

URL:
~~~~
    */applications/{id}*

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
        #. decision (Object)
        #. create_date (Number)
        #. update_date (Number)
        #. application_form (Object)
        #. submitted_user (Object)
        #. regarding_user (Object)
        #. registration_number (Number)
        #. handled_user (Object)

.. note::

    decision relates to Decision entity

    *_date represent as number of seconds since 1 Jan 1970 00:00:00 (UTC)

    application_form relates to ApplicationForm entity

    submitted_user, regarding_user relate to User entity

    handled_user relates to Person

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "decision":{},
        "create_date":1461239571000,
        "update_date":1461297884000,
        "application_form":{},
        "submitted_user":{},
        "regarding_user":{},
        "registration_number":1111,
        "handled_user":{}
    }

.. _`Get applications`:

Get applications
----------------

URL:
~~~~
    */applications*

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
    Array consists of objects from `Get application`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save application`:

Save application
----------------

URL:
~~~~
    */applications*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get application`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update application`:

Update application
------------------

URL:
~~~~
    */applications/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get application`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete application`:

Delete applicationForm
----------------------

URL:
~~~~
    */applications/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

