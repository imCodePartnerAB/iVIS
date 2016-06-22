ApplicationForms
================

``(implementation of ApplicationForm entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get applicationForm`_
    * `Get applicationForms`_
    * `Save applicationForm`_
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
        #. id (Number)
        #. name (String)
        #. version (Number)
        #. steps (Array)
        #. applications (Array)

.. note::

    steps relates to ApplicationFormStep entity

    applications relates to Application entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "version":1,
        "steps":[],
        "applications":[]
    }

.. _`Get applicationForms`:

Get applicationForms
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

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save applicationForm`:

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
    *Object*

    *With properties what you want to create, except id*

.. seealso::
    Whole properties list you can see at `Get applicationForm`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update applicationForms`:

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
    *Object*

    *With properties what you want to update, except id*

.. seealso::
    Whole properties list you can see at `Get applicationForm`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete applicationForms`:

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
    *Object*

