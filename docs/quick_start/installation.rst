Software installation
=====================

Process will be describe in Ubuntu Terminal (Ctrl+Alt+T), and all following code you need input there.

Install Git
~~~~~~~~~~~

.. code-block:: bash

    sudo apt-get install git-all

Clone project from GitHub repository
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: bash

    cd /home/username/directory #Directory in what you want to clone project

    git clone https://github.com/imCodePartnerAB/iVIS.git

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

We recommends download `8 version <https://tomcat.apache.org/download-80.cgi/>`_

Extract Tomcat folder from archive, in place what you want.





