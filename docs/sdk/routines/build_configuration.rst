Build configuration
===================

As build tool we would use `Maven <https://maven.apache.org/>`_.

In `Get started <http://docs.ivis.se/en/latest/sdk/get_started.html>`_ described iVIS dependency management.

iVIS now uses Jackson 2.4, it isn't compatible with latest version that's why also in pom.xml we must include/override Jackson as dependency.

.. code-block:: xml

    <properties>
        <jackson.version>2.4.0</jackson.version>
    </properties>

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>${jackson.version}</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>${jackson.version}</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>${jackson.version}</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.datatype</groupId>
        <artifactId>jackson-datatype-jsr310</artifactId>
        <version>${jackson.version}</version>
    </dependency>
