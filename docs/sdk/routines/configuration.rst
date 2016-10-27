Configuration
=============

We will use Spring for configuration and also Dependency Injection principle.

Additionally to your configuration you will needed:

    #. `Properties`_
    #. `Beans for injection`_
        * `Java config`_
        * `XML config`_

Properties
----------

.. code-block:: properties

    api-server-address = http://ivis.dev.imcode.com
    client-address = http://client.of.ivis.com
    client-id = ff11397c-3e3b-4398-80a9-feba203f1928
    redirect-uri = ${client-address}/redirecttome
    user-authorization-uri = ${api-server-address}/oauth/authorize
    access-token-uri = ${api-server-address}/oauth/token

Beans for injection
-------------------

Java config
~~~~~~~~~~~

.. literalinclude:: /sdk/routines/code/BeansContext.java
    :language: java
    :linenos:


