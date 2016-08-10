Walkthrough: Install iVIS Server
================================

Prerequisites
_____________

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

iVIS maven project from GitHub consists of 4 modules:

    #. ivis-core - entities and service interfaces for them;
    #. ivis-sdk - sdk for easier work with the API;
    #. ivis-server - iVIS server, implementation of the application logic;
    #. ivis-client - an example of iVIS client based on imCMS.

Git configuration
-----------------

Clone project from GitHub repository:

.. code-block:: bash

    cd /home/username/directory #Directory in what you want to clone project

    git clone https://github.com/imCodePartnerAB/iVIS.git

First of all you need switch to the branch of the latest changes. This branch is NewApplicationFormArchitect. Run following commands in the Terminal for that:

.. code-block:: bash

    cd /home/../iVIS #path to iVIS project directory

    git checkout NewApplicationFormArchitect

Database configuration
----------------------

In the Terminal (Ctrl+ALt+T) input following commands to create the database:

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE {databaseName}; #{databaseName} - database name

Edit file **server.properties** from *ivis-server/src/main/webapp/WEB-INF* in the next way:

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

Put file :download:`import.sql <../files/import.sql>` into *ivis-server/src/main/resources*.

Run application
---------------

In the Terminal (Ctrl+ALt+T) execute following commands:

.. code-block:: bash

    cd /home/../iVIS #path to iVIS project directory

    mvn clean install -DskipTests

Go to project folder (/home/../iVIS/ivis-server/target).

Find there file iVIS.war, rename to ivis.war.

Copy file to directory where you download Tomcat (/home/../apache-tomcat-{version}/webapps).

If you have process on port 8080 you must kill it by executing following command in the Terminal:

.. code-block:: bash

    fuser -k 8080/tcp

Then go to Tomcat folder (/home/../apache-tomcat-{version}/bin).

Run Tomcat by executing following command in the Terminal:

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x startup.sh

    ./startup.sh

Open the iVIS Server administration console in your browser: http://localhost:8080/ivis.

Login: admin

Password: password

.. note::
    Default installations of the iVIS Server have configured user and person related in the database.
    You can read more about it `here </en/latest/api/authorization.html>`_.

If you see this image, everything is good, congratulations!

.. image:: /images/ivisServerStartPage.png

Shutdown Tomcat by executing following command in the Terminal:

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x shutdown.sh

    ./shutdown.sh







