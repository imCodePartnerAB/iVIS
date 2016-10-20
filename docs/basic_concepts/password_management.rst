Password management
===================

    * `Password handling`_
         * `Saving Password`_
         * `Password in authorization context`_
    * `Password policy`_

Password handling
-----------------

Passwords handling divide into saving password and process password in authorization context.

Saving Password
~~~~~~~~~~~~~~~

Password is handled by **Spring MVC Controller**.
After form submitting from password generate bcrypt hash and hash persisted to database.

Password in authorization context
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

In authorization process passwords are handled by **Spring Security** standard filters request.
Handling means get password from login page, compare password with hash from database.

Password policy
---------------

Passwords must have **8 characters**.

Check for password strength is absent.




