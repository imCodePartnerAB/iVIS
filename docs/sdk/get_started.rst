.. |copy| unicode:: 0xA9
Get started
===========

    * `Add Maven repository`_
    * `Add Maven dependencies`_

iVIS SDK consists of ivis-core, ivis-services and ivis-sdk dependencies.
To use them you need include dependencies from Imcode |copy| maven repository to your Maven configuration file (pom.xml).

Add Maven repository
--------------------

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

Add Maven dependencies
----------------------

.. code-block:: xml

    <properties>
        <ivis.version>1.0.0-alpha2</ivis.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.imcode.ivis</groupId>
            <artifactId>ivis-core</artifactId>
            <version>${ivis.version}</version>
        </dependency>
        <dependency>
            <groupId>com.imcode.ivis</groupId>
            <artifactId>ivis-sdk</artifactId>
            <version>${ivis.version}</version>
        </dependency>
        <dependency>
            <groupId>com.imcode.ivis</groupId>
            <artifactId>ivis-services</artifactId>
            <version>${ivis.version}</version>
        </dependency>
    </dependencies>
