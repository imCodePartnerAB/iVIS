Walkthrough: Install iVIS Server Client Application
===================================================

Prerequisites
-------------

First of all you need install iVIS server. Visit `this page </en/latest/quick_start/walkthrough_install_ivis_server.html>`_
for details. When you have working iVIS server we can continue, but to make it possible to communicate with the iVIS
Server from the client application you need to register your client application inside the iVIS Server.
Here is the `walkthrough </en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ that describes this process.

This client is based on imCMS.

Database configuration
----------------------

In the Terminal (Ctrl+ALt+T) execute following commands to create the database:

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE db_ivis_info;

Download file :download:`dump_db_ivis_info.sql <../files/dump_db_ivis_info.sql>`.

In the Terminal (Ctrl+ALt+T) execute following command to run dump file:

.. code-block:: bash

    mysql -u {username} -p{password} db_ivis_info < /home/downloads/dump_db_ivis_info.sql #set where is file located

Run application
---------------

Go to project folder (/home/../iVIS/ivis-client/target).

Find there file ivis-client.war, rename to client.war.

Copy file to directory where you download Tomcat (/home/../apache-tomcat-{version}/webapps).

Run Tomcat by executing following command in the Terminal:

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x startup.sh

    ./startup.sh

Open iVIS Сlient in your browser: http://localhost:8080/client.

Login: admin

Password: password

.. note::
    Default installations of the iVIS Server and iVIS Client have configured client in the database.
    You can read more about it `here </en/latest/api/authorization.html>`_.

If you see this image, everything is good, congratulations!

.. image:: /images/ivisClientStartPage.png

Shutdown Tomcat by executing following command in the Terminal.

.. code-block:: bash

    cd  /home/../apache-tomcat-{version}/bin

    chmod +x shutdown.sh

    ./shutdown.sh



