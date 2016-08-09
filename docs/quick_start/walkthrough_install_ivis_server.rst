Walkthrough: Install iVIS Server
================================
*For install iVIS server you need*:

 * `Git <https://git-scm.com//>`_
 * `iVIS project <https://github.com/imCodePartnerAB/iVIS/>`_
 * `Java <https://www.oracle.com/java/>`_
 * `MySQL database <http://www.mysql.com/>`_
 * `Maven <https://maven.apache.org/>`_
 * `Tomcat server <https://tomcat.apache.org//>`_

For details instruction of install this components visit page:

.. toctree::
    :titlesonly:

    installation

Project structure
-----------------

iVIS maven project from GitHub consist of 4 modules:
    #. *ivis-core* - entities and service interfaces for them
    #. *ivis-sdk* - sdk for easier work with API
    #. *ivis-server* - iVIS server, where is implemented all logic of site
    #. *ivis-client* - an example of iVIS client based on imCMS

Git configuration
-----------------

First of all you need switch to branch of latest changes.

Current branch is NewApplicationFormArchitect

In Terminal(Ctrl+ALt+T) input following commands

.. code-block:: bash

    cd /home/../iVIS #path to iVIS project directory

    git checkout NewApplicationFormArchitect

Database configuration
----------------------

In Terminal(Ctrl+ALt+T) input following commands for create database

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE {databaseName}; #{databaseName} - database name

Edit file **server.properties** from *ivis-server/src/main/webapp/WEB-INF* following

    * User [#]_
    * Password [#]_
    * JdbcUrl [#]_
    * Hibernate.hbm2ddl [#]_
    * Hibernate.dialect [#]_
    * Server.name [#]_

.. [#] **username** of database (default *root*)
.. [#] **password** of database
.. [#] consist  of **jdbc:{provider}://{hostname}:{port}/{databaseName}?{encoding}**
    for mysql {provider} = *mysql*, {hostname} = *localhost*, {port} = 3306, {encoding} = *utf-8*
.. [#] set *create*
.. [#] for mysql is *org.hibernate.dialect.MySQL5InnoDBDialect*
.. [#] **host** of server (default *http://localhost:8080*)

Put file :download:`import.sql <../files/import.sql>` into *ivis-server/src/main/resources*

Run application
---------------

In Terminal(Ctrl+ALt+T) input following commands

    cd /home/../iVIS #path to iVIS project directory

    mvn clean install -DskipTests






