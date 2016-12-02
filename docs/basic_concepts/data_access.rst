Data Access Layer
=================

Data access layer in iVIS is implemented using standard JPA (using Hibernate as ORM provider).

All entities are mapped to the corresponding tables. All tables (except authorization ones) have prefix "dbo_" that means "database object".
Except of authorization tables
(it relate with ideological considerations).

**Database diagram** :download:`download <../images/databaseDiagram.gif>`.

JPA classes located in *com.imcode.entities* of ivis-core module in iVIS project.

**Class diagram** :download:`download <../images/classDiagram.png>`.

**JPA entities diagram** :download:`download <../images/jpaEntitiesDiagram.png>`.

Relations in DB also are implement by standard JPA.

.. note::

    Embedded entities don't have id and can't exist without parent entity.

List of all tables and description
----------------------------------

dbo_academic_year
~~~~~~~~~~~~~~~~~
Represents AcademicYear entity.

dbo_activity
~~~~~~~~~~~~
Represents Activity entity.

dbo_after_school_center_section
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents AfterSchoolCenterSection entity.

dbo_application
~~~~~~~~~~~~~~~
Represents Application entity.

dbo_application_form
~~~~~~~~~~~~~~~~~~~~
Represents ApplicationForm entity.

dbo_application_form_question
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents ApplicationFormQuestion entity.

dbo_application_form_question_group
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents ApplicationFormQuestionGroup entity.

dbo_application_form_step
~~~~~~~~~~~~~~~~~~~~~~~~~
Represents ApplicationFormStep entity.

dbo_category
~~~~~~~~~~~~
Represents Category entity.

dbo_entity_version
~~~~~~~~~~~~~~~~~~
Represents EntityVersion entity.

dbo_guardian
~~~~~~~~~~~~
Represents Guardian entity.

dbo_incident
~~~~~~~~~~~~
Represents Incident entity.

dbo_incident_category_cross
~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Incident and Category entities.

dbo_incident_pupil_cross
~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Incident and Pupil entities.

dbo_issue
~~~~~~~~~
Represents Issue entity.

dbo_issue_category_cross
~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Issue and Category entities.

dbo_issue_pupil_cross
~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Issue and Pupil entities.

dbo_issues_authorized_persons_cross
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Issue and Person entities.

dbo_log_event
~~~~~~~~~~~~~
Represents LogEvent entity.

dbo_person
~~~~~~~~~~
Represents Person entity.

dbo_person_address
~~~~~~~~~~~~~~~~~~
Represents Address embeddable in Person.

dbo_person_email
~~~~~~~~~~~~~~~~
Represents Email embeddable in Person.

dbo_person_phone
~~~~~~~~~~~~~~~~
Represents Phone embeddable in Person.

dbo_priority
~~~~~~~~~~~~
Represents Priority entity.

dbo_pupil
~~~~~~~~~
Represents Pupil entity.

dbo_pupil_after_school_center_schema
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents AfterSchoolCenterSchema embeddable in Pupil.

dbo_pupil_guardians_cross
~~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Pupil and Guardian entities.

dbo_role
~~~~~~~~
Represents Role entity.

dbo_role_permission_cross
~~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between Role and Permission entities.

dbo_school
~~~~~~~~~~
Represents School entity.

dbo_school_class
~~~~~~~~~~~~~~~~
Represents SchoolClass entity.

dbo_school_class_diaries
~~~~~~~~~~~~~~~~~~~~~~~~
Represents Diary embeddable in SchoolClass.

dbo_school_service_cross
~~~~~~~~~~~~~~~~~~~~~~~~
Represents ServiceTypeEnum element collection, which equals embeddable in School.

dbo_school_transport
~~~~~~~~~~~~~~~~~~~~
Represents SchoolTransport entity.

dbo_status
~~~~~~~~~~
Represents Status entity.

dbo_truancy
~~~~~~~~~~~
Represents Truancy entity.

dbo_user
~~~~~~~~
Represents User entity.

dbo_user_roles_cross
~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between User and Role entities.

AUTHORIZATION TABLES
--------------------

oauth_access_token
~~~~~~~~~~~~~~~~~~
Represents AccessToken entity. Managed by Spring Security.

oauth_refresh_token
~~~~~~~~~~~~~~~~~~~
Represents RefreshToken entity. Managed by Spring Security.

dbo_oauth_client_details
~~~~~~~~~~~~~~~~~~~~~~~~
Represents JpaClientDetails entity.

dbo_oauth_client_additional_info
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Element collection in JpaClientDetails.

dbo_oauth_client_grant_types
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Element collection in JpaClientDetails.

dbo_oauth_client_redirect_uris
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Element collection in JpaClientDetails.

dbo_oauth_client_resources
~~~~~~~~~~~~~~~~~~~~~~~~~~
Element collection in JpaClientDetails.

dbo_oauth_client_roles_cross
~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Represents many to many relation between JpaClientDetails and Role entities.