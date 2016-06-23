Data Access layer
=================

Data access layer in iVIS implemented using JPA standard (ORM provider Hibernate).

All tables present as mapping of entities.
Tables have prefix "dbo_" that means database object,
except of authorization tables (it relate with ideological considerations).

**Database diagram** :download:`download <../images/databaseDiagram.gif>`.

JPA classes located in *com.imcode.entities* of ivis-core module in iVIS project.

**Class diagram** :download:`download <../images/classDiagram.png>`.

**JPA entities diagram** :download:`download <../images/jpaEnitiesDiagram.png>`.

Relations in DB also implement by JPA standard.

.. note::

    embeddables have not id, because they can not exist without entity

**List of all tables and description:**

    dbo_academic_year (represents AcademicYear entity)

    dbo_activity (represents Activity entity)

    dbo_after_school_center_section (represents AfterSchoolCenterSection entity)

    dbo_app_role (represents ApplicationRole entity)

    dbo_application (represents Application entity)

    dbo_application_form (represents ApplicationForm entity)

    dbo_application_form_question (represents ApplicationFormQuestion entity)

    dbo_application_form_question_group (represents ApplicationFormQuestionGroup entity)

    dbo_application_form_step (represents ApplicationFormStep entity)

    dbo_category (represents Category entity)

    dbo_contact_person (represents ContactPerson entity)

    dbo_contact_person_address (represents Address embeddable for ContactPerson)

    dbo_contact_person_email (represents Email embeddable for ContactPerson)

    dbo_contact_person_phone (represents Phone embeddable for ContactPerson)

    dbo_entity_version (represents EntityVersion entity)

    dbo_guardian (represents Guardian entity)

    dbo_incident (represents Incident entity)

    dbo_incident_category_cross (represents many to many relation between Incident and Category entities)

    dbo_incident_person_cross (represents many to many relation between Incident and Person entities)

    dbo_incident_pupil_cross (represents many to many relation between Incident and Pupil entities)

    dbo_issue (represents Issue entity)

    dbo_issue_category_cross (represents many to many relation between Issue and Category entities)

    dbo_issue_pupil_cross (represents many to many relation between Issue and Pupil entities)

    dbo_issues_authorized_persons_cross (represents many to many relation between Issue and Person entities)

    dbo_log_event (represents LogEvent entity)

    dbo_person (represents Person entity)

    dbo_person_address (represents Address embeddable in Person)

    dbo_person_email (represents Email embeddable in Person)

    dbo_person_phone (represents Phone embeddable in Person)

    dbo_priority (represents Priority entity)

    dbo_pupil (represents Pupil entity)

    dbo_pupil_after_school_center_schema (represents AfterSchoolCenterSchema embeddable in Pupil)

    dbo_pupil_guardians_cross (represents many to many relation between Pupil and Guardian entities)

    dbo_role (represents Role entity)

    dbo_school (represents School entity)

    dbo_school_class (represents SchoolClass entity)

    dbo_school_class_diaries (represents Diary embeddable in SchoolClass)

    dbo_school_service_cross (represents ServiceTypeEnum element collection, which equals embeddable in School)

    dbo_school_transport (represents SchoolTransport entity)

    dbo_semester (represents Semester entity)

    dbo_status (represents Status entity)

    dbo_truancy (represents Truancy entity)

    dbo_user (represents User entity)

    dbo_user_roles_cross (represents many to many relation between User and Role entities)

*Authorization tables:*

    oauth_access_token

    oauth_refresh_token

    dbo_oauth_client_details (represents JpaClientDetails entity)

    dbo_oauth_client_additional_info (element collection in JpaClientDetails)

    dbo_oauth_client_garant_types (element collection in JpaClientDetails)

    dbo_oauth_client_redirect_uris (element collection in JpaClientDetails)

    dbo_oauth_client_resources (element collection in JpaClientDetails)

    dbo_oauth_client_roles_cross (represents many to many relation between JpaClientDetails and Role entities)

    dbo_oauth_client_scope (element collection in JpaClientDetails)