ApplicationFormSteps
====================

``(implementation of ApplicationFormStep entity)``

Provides following methods for `API <index.html>`_ calls:

    * `Get applicationFormStep`_
    * `Get applicationFormSteps`_
    * `Save applicationFormStep`_
    * `Update applicationFormStep`_
    * `Delete applicationFormStep`_

.. _`Get applicationFormStep`:

Get applicationFormStep
-----------------------

URL:
~~~~
    */applicationformsteps/{id}*

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
        #. text (String)
        #. sort_order (Number)
        #. question_groups (Array)
        #. application_form (Object)

.. note::

    sort_order represent unique number according to sort purpose

    question_groups relates to ApplicationFormStepGroup entity

    application_form  relates to ApplicationForm entity

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
        "id":1,
        "name":"Name",
        "text":"Definition",
        "sort_order":1,
        "question_groups":[],
        "application_form":{}
    }

.. _`Get applicationFormSteps`:

Get applicationFormSteps
------------------------

URL:
~~~~
    */applicationformsteps*

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
Array consists of objects from `Get applicationFormStep`_ method

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    [
        {},
        {},
        ...
        {}
    ]

.. _`Save applicationFormStep`:

Save applicationFormStep
------------------------

URL:
~~~~
    */applicationformsteps*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to create, except id*

.. seealso::
Whole properties list you can see at `Get applicationFormStep`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

    *On server fill id*

.. _`Update applicationFormSteps`:

Update applicationFormStep
--------------------------

URL:
~~~~
    */applicationformsteps/{id}*

Method:
~~~~~~~
    *PUT*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Object*

    *With properties what you want to update, except id*

.. seealso::
Whole properties list you can see at `Get applicationFormStep`_

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

.. _`Delete applicationFormSteps`:

Delete applicationFormStep
--------------------------

URL:
~~~~
    */applicationformsteps/{id}*

Method:
~~~~~~~
    *DELETE*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *null*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Object*

