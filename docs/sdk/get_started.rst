.. |copy| unicode:: 0xA9
Get started
===========

    * `Add repository`_
        * `Maven <http://docs.ivis.se/en/latest/sdk/get_started.html#mvn1>`_
        * `Gradle <http://docs.ivis.se/en/latest/sdk/get_started.html#grdl1>`_
    * `Add dependencies`_
        * `Maven <http://docs.ivis.se/en/latest/sdk/get_started.html#mvn2>`_
        * `Gradle <http://docs.ivis.se/en/latest/sdk/get_started.html#grdl2>`_


iVIS SDK consists of ivis-core and ivis-sdk dependencies.
To use them you need include dependencies from Imcode |copy| maven repository to your Maven or Gradle configuration file.

Add repository
--------------

.. _`mvn1`:

Maven
~~~~~

Write in your pom.xml

.. code-block:: xml

    <repositories>
        <repository>
            <id>imcode</id>
            <url>http://repo.imcode.com/maven2</url>
            <snapshots>
                <updatePolicy>always</updatePolicy>
                <enabled>true</enabled>
            </snapshots>
            <releases>
                <updatePolicy>always</updatePolicy>
                <enabled>true</enabled>
            </releases>
        </repository>
    </repositories>

.. _`grdl1`:

Gradle
~~~~~~

Write in your build.gradle

.. code-block:: js

    repositories {
        maven { url "http://repo.imcode.com/maven2" }
        mavenCentral()
    }

Add dependencies
----------------

.. _`mvn2`:

Maven
~~~~~

Write in your pom.xml

.. code-block:: xml

    <dependencies>
        <dependency>
            <groupId>com.imcode.ivis</groupId>
            <artifactId>ivis-core</artifactId>
            <version>1.0.0-alpha1-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>com.imcode.ivis</groupId>
            <artifactId>ivis-sdk</artifactId>
            <version>1.0.0-alpha1-SNAPSHOT</version>
        </dependency>
    </dependencies>

.. _`grdl2`:

Gradle
~~~~~~

Write in your build.gradle

.. code-block:: js

    dependencies {
        compile group:'com.imcode.ivis', name:'ivis-core', version:'1.0.0-alpha1-SNAPSHOT'
        compile group:'com.imcode.ivis', name:'ivis-sdk',version:'1.0.0-alpha1-SNAPSHOT'
    }