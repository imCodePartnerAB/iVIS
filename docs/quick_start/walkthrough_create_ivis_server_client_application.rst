Walkthrough: Create iVIS Server Client Application
==================================================

Prerequisites
_____________

This client based on imCMS.

First of all you need install iVIS server.

For details visit `this page </en/latest/quick_start/walkthrough_install_ivis_server.html>`_ .

If you run iVIS server, than can continue, otherwise you can't move on.

Database configuration
----------------------

In Terminal(Ctrl+ALt+T) input following commands for create database

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE db_ivis_info;

Download file :download:`dump_db_ivis_info.sql <../files/dump_db_ivis_info.sql>`

In Terminal(Ctrl+ALt+T) input following command for run dump file on database

.. code-block:: bash

    mysql -u {username} -p{password} db_ivis_info < /home/downloads/dump_db_ivis_info.sql #set where is file located

Run application
---------------

Go to project folder (/home/../iVIS/ivis-client/target).

Find there file ivis-client.war, rename to client.war.

Copy file to directory where you download Tomcat (/home/../apache-tomcat-{version}/webapps).

Run Tomcat by input following command in Terminal.

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x startup.sh

    ./startup.sh

Open in your browser http://localhost:8080/client .

Login: admin
Password: password

If you see this image, everything is good, congratulations!

.. image:: /images/ivisClientStartPage.png

Shutdown Tomcat by input following command in Terminal.

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x shutdown.sh

    ./shutdown.sh



