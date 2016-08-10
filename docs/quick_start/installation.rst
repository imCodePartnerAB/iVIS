Software Installation
=====================

Process will be describe in Ubuntu Terminal (Ctrl+Alt+T), and all following code you need input there.

Install Git
~~~~~~~~~~~

.. code-block:: bash

    sudo apt-get install git-all

Install Java
~~~~~~~~~~~~

Project uses Java 1.8, so we recommend you to install Oracle JDK 1.8

.. code-block:: bash

    sudo add-apt-repository ppa:webupd8team/java #Add repository to your list

    sudo apt-get update

    sudo apt-get install oracle-java8-installer

Then follow instruction in a console.

Install MySQL server and client
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: bash

    sudo apt install mysql-server mysql-client

    sudo apt-get install libmysql-java #jdbc connectors

Enter configuration properties such as password.

Install Maven
~~~~~~~~~~~~~

.. code-block:: bash

    sudo apt-get install maven

Download Tomcat server
~~~~~~~~~~~~~~~~~~~~~~

You can download it from the `official site <https://tomcat.apache.org//>`_

Or use this one: :download:`apache-tomcat-8.5.4.tar.gz <../files/apache-tomcat-8.5.4.tar.gz>`.

Extract Tomcat folder from the archive in the location you want.





