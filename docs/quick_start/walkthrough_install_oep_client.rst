Walkthrough: Install OeP based Client application
=================================================

Prerequisites
-------------

First of all you need install iVIS server. Visit `this page </en/latest/quick_start/walkthrough_install_ivis_server.html>`_
for details. When you have working iVIS server we can continue, but to make it possible to communicate with the iVIS
Server from the client application you need to register your client application inside the iVIS Server.
Here is the `walkthrough </en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ that describes this process.

This client is based on `OeP <http://www.oeplatform.org>`_.

Git configuration
-----------------

Clone project from GitHub repository:

.. code-block:: bash

    cd /../directory #Directory where project folder with content must be placed

    git clone https://github.com/imCodePartnerAB/iVIS-OeP-Client-Sample.git

After cloning will created subdirectory iVIS with content.

Database configuration
----------------------

In the Terminal (Ctrl+ALt+T) execute following commands to create the database:

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE db_open_platform;

Download file `dump_db_open_platform.sql <https://github.com/imCodePartnerAB/iVIS-OeP-Client-Sample/releases/download/v1.0.0-alpha1/dump_db_open_platform.sql>`_
from release on Github.

In the Terminal (Ctrl+ALt+T) execute following command to run dump file on created DB:

.. code-block:: bash

    mysql -u {username} -p{password} db_open_platform < /home/downloads/dump_db_open_platform.sql #file location after downloading

Edit file **config.xml** from *demo.oeplatform.org/src/main/webapp/WEB-INF* subfolder in the next way:

    * <Username>${username}</Username> [#]_;
    * <Password>${password}</Password> [#]_;
    * <Url>${jdbc-url}</Url> [#]_;

.. [#] **username** of database (default *root*)
.. [#] **password** of database
.. [#] consist  of **jdbc:{provider}://{hostname}:{port}/{databaseName}?{encoding}**
    for mysql {provider} = *mysql*, {hostname} = *localhost*, {port} = 3306, {encoding} = *utf-8*

Run application
---------------

.. important::
    iVIS Server must be installed from `this </en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ guide.

In the Terminal (Ctrl+ALt+T) execute following commands:

.. code-block:: bash

    cd /../iVIS-OeP-Client-Sample #path to project from Github

    mvn clean install

    cd demo.oeplatform.org

    mvn tomcat7:deploy #deploy configured to localhost:8080/oepcl.

Type http://localhost:8080/oepcl in browser.

Click "Logga in".

Input Login=admin and Password=pass.

If you see this image, everything is good, congratulations!

.. image:: /images/oepClientStartPage.png





