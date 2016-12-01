Walkthrough: Install iVIS Server
================================

Prerequisites
-------------

To install iVIS server you need:

 * `Git <https://git-scm.com//>`_;
 * `iVIS project <https://github.com/imCodePartnerAB/iVIS/>`_;
 * `Java <https://www.oracle.com/java/>`_;
 * `MySQL database <http://www.mysql.com/>`_;
 * `Maven <https://maven.apache.org/>`_;
 * `Tomcat server <https://tomcat.apache.org//>`_.

For detailed instructions of installing this components visit `this page </en/latest/quick_start/installation.html>`_.

Project structure
-----------------

`iVIS <https://github.com/imCodePartnerAB/iVIS>`_ maven project from GitHub consists of 4 modules:

    #. ivis-core - entities that you can use in API;
    #. ivis-services - service interfaces for entities; they are define provided methods;
    #. ivis-sdk - sdk for easier work with the API;
    #. ivis-server - iVIS server, implementation of the application logic.

Git configuration
-----------------

Clone project from GitHub repository:

.. code-block:: bash

    cd /../directory #Directory where project folder with content must be placed

    git clone https://github.com/imCodePartnerAB/iVIS.git

After cloning will created subdirectory iVIS with content.

Database configuration
----------------------

In the Terminal (Ctrl+ALt+T) input following commands to create the database:

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE db_ivis_test; #or custom {databaseName}

Edit file **local.server.properties** from *ivis-server* subfolder in the next way:

    * User [#]_;
    * Password [#]_;
    * JdbcUrl [#]_;
    * Hibernate.hbm2ddl [#]_;
    * Hibernate.dialect [#]_;
    * Server.name [#]_;

.. [#] **username** of database (default *root*)
.. [#] **password** of database
.. [#] consist  of **jdbc:{provider}://{hostname}:{port}/{databaseName}?{encoding}**
    for mysql {provider} = *mysql*, {hostname} = *localhost*, {port} = 3306, {encoding} = *utf-8*
.. [#] set *create*
.. [#] for mysql is *org.hibernate.dialect.MySQL5InnoDBDialect*
.. [#] **host** of server (default *http://localhost:8080*)

Put file `import.sql <https://github.com/imCodePartnerAB/iVIS/releases/download/v1.0.0-alpha1/import.sql>`_
from Github project repository (v1.0.0-alpha1 release) into *ivis-server/src/main/resources*.

Run application
---------------

If you have process on port 8080 you must kill it by executing following command in the Terminal:

.. code-block:: bash

    fuser -k 8080/tcp

Then go to Tomcat folder (*{tomcat-folder}*).

.. important::
    In Tomcat must be configured user with roles: manager-script, manager-gui, admin, manager-jmx.
    They are configured in this :download:`instance </files/apache-tomcat-8.5.8.tar.gz>`.

.. note::
    In this manual uses username=admin and password=admin for that authority.

Run Tomcat by executing following command in the Terminal:

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x startup.sh

    ./startup.sh

Go to m2 local repository (user/.m2).

Create/edit file :download:`settings.xml </files/settings.xml>` in next way.

.. literalinclude:: /files/settings.xml
    :language: xml
    :linenos:
    :lineno-start: 5
    :lines: 5-11

In browser input address: http://localhost:8080/manager .

Enter login "admin" and password "admin".

Click Undeploy where root path ("/").

.. image:: /images/undeployPointer.png

In the Terminal (Ctrl+ALt+T) execute following commands:

.. code-block:: bash

    cd /../iVIS #path to iVIS project directory

    mvn tomcat7:deploy #deploy configured to localhost:8080.

Type http://localhost:8080 in browser.

Input Login=admin and Password=pass.

If you see this image, everything is good, congratulations!

.. image:: /images/ivisServerStartPage.png









