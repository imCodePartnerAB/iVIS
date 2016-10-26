Applications
============

``(implementation of Application entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get application`_
    * `Get applications`_
    * `Save application`_
    * `Save applications`_
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
        #. id(NUMBER)
        #. create_date(NUMBER)
        #. update_date(NUMBER)
        #. application_form(OBJECT)
           type of `ApplicationForm <http://docs.ivis.se/en/latest/api/applicationform.html>`_
        #. submitted_user(OBJECT)
           type of `User <http://docs.ivis.se/en/latest/api/user.html>`_
        #. regarding_user(OBJECT)
           type of `User <http://docs.ivis.se/en/latest/api/user.html>`_
        #. registration_number(NUMBER)
        #. decision(OBJECT)
            type of `Decision <http://docs.ivis.se/en/latest/api/decision.html>`_
        #. handled_user(OBJECT)
           type of `Person <http://docs.ivis.se/en/latest/api/person.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "create_date" : 1477474353064,
      "update_date" : 1477474353064,
      "application_form" : {
        "id" : null,
        "name" : null,
        "version" : null,
        "steps" : [ ]
      },
      "submitted_user" : {
        "id" : null,
        "name" : null,
        "person" : null,
        "roles" : [ ],
        "saml2_id" : null
      },
      "regarding_user" : {
        "id" : null,
        "name" : null,
        "person" : null,
        "roles" : [ ],
        "saml2_id" : null
      },
      "registration_number" : 0,
      "decision" : null,
      "handled_user" : {
        "id" : null,
        "personal_id" : null,
        "first_name" : null,
        "last_name" : null,
        "addresses" : { },
        "emails" : { },
        "phones" : { }
      }
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
    *OBJECT(Application)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Application)*

Null properties:
~~~~~~~~~~~~~~~~
    *id*

Save applications
-----------------

URL:
~~~~
    */applications*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(Application)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(Application)*
Null properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

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
    *OBJECT(Application)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(Application)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete application`:

Delete application
------------------

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
    *OBJECT(Application)*

.. note::

    you receive deleted object


