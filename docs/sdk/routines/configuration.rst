Configuration
=============

We will use Spring for configuration and also Dependency Injection principle.

For configuration you will need:

    #. `Properties`_
    #. `Beans for injection`_
        * `Java config`_
        * `XML config`_

Properties
----------

.. code-block:: properties

    api-server-address = http://ivis.dev.imcode.com
    client-address = http://client.of.ivis.com
    #client id that you receive form system administrator
    client-id = ff11397c-3e3b-4398-80a9-feba203f1928
    redirect-relate-uri = /redirecttome
    user-authorization-relate-uri = /oauth/authorize
    access-token-relate-uri = /oauth/token

Beans for injection
-------------------

Java config
~~~~~~~~~~~

.. literalinclude:: /sdk/routines/code/BeansContext.java
    :language: java
    :linenos:

XML config
~~~~~~~~~~

.. literalinclude:: /sdk/routines/code/beansContext.xml
    :language: xml
    :linenos:
