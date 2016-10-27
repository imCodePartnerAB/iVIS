ApplicationFormSteps
====================

``(implementation of ApplicationFormStep entity)``

Provides following method for `API <index.html>`_ calls:

    * `Get applicationFormStep`_
    * `Get applicationFormSteps`_
    * `Save applicationFormStep`_
    * `Save applicationFormSteps`_
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
        #. id(NUMBER)
        #. name(STRING)
        #. sort_order(NUMBER)
        #. text(STRING)
        #. question_groups(ARRAY)
           type of `ApplicationFormQuestionGroup <http://docs.ivis.se/en/latest/api/applicationformquestiongroup.html>`_
        #. application_form(OBJECT)
           type of `ApplicationForm <http://docs.ivis.se/en/latest/api/applicationform.html>`_

Example of response:
~~~~~~~~~~~~~~~~~~~~

.. code-block:: json

    {
      "id" : 0,
      "name" : "",
      "sort_order" : 0,
      "text" : "",
      "question_groups" : [ ],
      "application_form" : {
        "id" : null,
        "name" : null,
        "version" : null
      }
    }

.. _`Get applicationformsteps`:

Get applicationformsteps
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
    *OBJECT(ApplicationFormStep)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormStep)*

On server fill properties:
~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

Save applicationformsteps
-------------------------

URL:
~~~~
    */applicationformsteps*

Method:
~~~~~~~
    *POST*

Parameters request:
~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormStep)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *Array(ApplicationFormStep)*
On server fill properties of every object in array:
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *id*

.. _`Update applicationFormStep`:

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
    *OBJECT(ApplicationFormStep)*

Parameters response:
~~~~~~~~~~~~~~~~~~~~
    *OBJECT(ApplicationFormStep)*

.. note::

    property will be updated, if you don't want update property it need set null

.. _`Delete applicationFormStep`:

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
    *OBJECT(ApplicationFormStep)*

.. note::

    you receive deleted object


