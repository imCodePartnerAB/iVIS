Walkthrough: Install Imcms based client application
===================================================

Prerequisites
-------------

First of all you need install iVIS server. Visit `this page </en/latest/quick_start/walkthrough_install_ivis_server.html>`_
for details. When you have working iVIS server we can continue, but to make it possible to communicate with the iVIS
Server from the client application you need to register your client application inside the iVIS Server.
Here is the `walkthrough </en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ that describes this process.

This client is based on `imCMS <http://docs.imcms.net>`_.

Git configuration
-----------------

Clone project from GitHub repository:

.. code-block:: bash

    cd /../directory #Directory where project folder with content must be placed

    git clone https://github.com/imCodePartnerAB/iVIS-imCMS-Client-Sample.git

After cloning will created subdirectory iVIS with content.

Database configuration
----------------------

In the Terminal (Ctrl+ALt+T) execute following commands to create the database:

.. code-block:: bash

    mysql -u {username} -p{password} #{username} - database username, {password} - database password

    CREATE DATABASE db_ivis_info;

Download file  `dump_db_ivis_info.sql <https://github.com/imCodePartnerAB/iVIS-imCMS-Client-Sample/releases/download/v1.0.0-alpha1/dump_db_ivis_info.sql>`_
from release on Github.

In the Terminal (Ctrl+ALt+T) execute following command to run dump file on created DB:

.. code-block:: bash

    mysql -u {username} -p{password} db_ivis_info < /home/downloads/dump_db_ivis_info.sql #file location after downloading

Edit file **local.server.properties** from project directory in the next way:

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

Run application
---------------

.. important::
    iVIS Server must be installed from `this </en/latest/quick_start/walkthrough_ivis_server_conf.html>`_ guide.

In the Terminal (Ctrl+ALt+T) execute following commands:

.. code-block:: bash

    cd /../iVIS-imCMS-Client-Sample #path to project from Github

    mvn tomcat7:deploy #deploy configured to localhost:8080/imcmscl.

Type http://localhost:8080/imcmscl in browser.

Input Login=admin and Password=pass.

If you see this image, everything is good, congratulations!

.. image:: /images/imcmsClientStartPage.png





