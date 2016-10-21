Database Layer
==============

    * `Database information`_
    * `Integrity`_

Database information
--------------------

Currently iVIS database represented by `MySQL <http://www.mysql.com/>`_.

You can change it in properties (*JdbcDriver*, *JdbcUrl*, *Hibernate.dialect*).

.. note::
    Using something other database instead MySQL not tested.

Integrity
---------

Reporting about changing of information storing in DB something other way (e.g. manually run sql script) instead
Hibernate is missing.

If database has not mapped on entity columns, they are ignored by Hibernate.
