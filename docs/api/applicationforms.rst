ApplicationForms
================

``(implementation of ApplicationForm entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get applicationForm`_
    * `Get applicationForms`_
    * `Save applicationForm`_
    * `Save applicationForms`_
    * `Update applicationForm`_
    * `Delete applicationForm`_

.. _`Get applicationForm`:

Get applicationForm
-------------------

URL:
~~~~
    */applicationforms/{id}*

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
        #. version(NUMBER)
        #. steps(ARRAY)
           type of `ApplicationFormStep <http://docs.ivis.se/en/latest/api/applicationformstep.html>`_
        #. applications(ARRAY)
           type of `Application <http://docs.ivis.se/en/latest/api/application.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "version" : 0,
      "steps" : [ ],
      "applications" : null
    }

.. _`Get applicationforms`:

Get applicationforms
--------------------

URL:
~~~~
    */applicationforms*

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

    Array consists of objects from `Get applicationForm`_ method

Save applicationForm
--------------------

URL:
~~~~
    */applicationforms*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationForm)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationForm)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save applicationforms
---------------------

URL:
~~~~
    */applicationforms*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationForm)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationForm)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update applicationForm`:

Update applicationForm
----------------------

URL:
~~~~
    */applicationforms/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationForm)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationForm)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete applicationForm`:

Delete applicationForm
----------------------

URL:
~~~~
    */applicationforms/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationForm)*

.. note::

    you receive deleted object

