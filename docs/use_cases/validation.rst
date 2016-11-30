Validation
==========

    * `Client side`_
    * `Server side`_

Client side
-----------

To make client side validation need define rules for special form (see `jQuery Validation Plugin <https://jqueryvalidation.org/>`_).

Example of how to do it you can find at `restore_password_validation.js <https://github.com/imCodePartnerAB/iVIS/blob/25e0d0bd3b43fea115e8fc2cdfdaf411064c0c3d/ivis-server/src/main/webapp/WEB-INF/web-resources/js/restore_password_validation.js>`_

.. code-block:: js
    :linenos:
    :lineno-start: 30

    $('#restorePasswordFormEmail').validate({

        rules: {

            email: {
                required: true,
                email: true,
                checkNotUnique: "/restore_password/emailunique"
            }

        },

        messages: {

            email: {
                required: "Email is required",
                email: "Email not valid",
                checkNotUnique: "User with this email does not exist"
            }

        }

    });

Server side
-----------

Server side validation based on class `GeneralValidator <https://github.com/imCodePartnerAB/iVIS/blob/ae67abdd723e52c67c04a2410964f30c9b52868e/ivis-server/src/main/java/com/imcode/validators/GeneralValidator.java>`_.
It uses interface Validator.

For API object validation need override method getFieldsConstraints() for example in `IncidentRestControllerImpl <https://github.com/imCodePartnerAB/iVIS/blob/d7773778cb1401fb48fa45626bb514a70199d99b/ivis-server/src/main/java/com/imcode/controllers/restful/IncidentRestControllerImpl.java>`_

.. code-block:: java
    :linenos:
    :lineno-start: 77

    @Override
    protected Map<String, Map<GeneralValidator.Constraint, String>> getFieldsConstraints() {
        Map<String, Map<GeneralValidator.Constraint, String>> fieldsConstraints = super.getFieldsConstraints();

        GeneralValidator.buildField(fieldsConstraints, "title",
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
        );

        GeneralValidator.buildField(fieldsConstraints, "description",
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
        );

        GeneralValidator.buildField(fieldsConstraints, "categories",
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GeneralValidator.buildField(fieldsConstraints, "pupils",
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        GeneralValidator.buildField(fieldsConstraints, "priority",
                new AbstractMap.SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null)
        );

        return fieldsConstraints;
    }

Example how create validation from `AdminController <https://github.com/imCodePartnerAB/iVIS/blob/ae67abdd723e52c67c04a2410964f30c9b52868e/ivis-server/src/main/java/com/imcode/controllers/html/AdminController.java>`_ for form parameters.

.. code-block:: java
    :linenos:
    :lineno-start: 236

    Map<String, Map<GeneralValidator.Constraint, String>> constraints = new HashMap<>();

    GeneralValidator.buildField(constraints, "password",
            new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
            new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4"),
            new SimpleEntry<>(GeneralValidator.Constraint.MATCH_WITH, "confirmPassword")
    );

    GeneralValidator.buildField(constraints, "person.firstName",
            new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
            new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
    );

    GeneralValidator.buildField(constraints, "person.lastName",
            new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
            new SimpleEntry<>(GeneralValidator.Constraint.MIN, "4")
    );

    GeneralValidator.buildField(constraints, "person.emails",
            new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
            new SimpleEntry<>(GeneralValidator.Constraint.REGEX, GeneralValidator.EMAIL_PATTERN)
    );

    GeneralValidator.buildField(constraints, "person.phones",
            new SimpleEntry<>(GeneralValidator.Constraint.NOT_NULL_OR_EMPTY, null),
            new SimpleEntry<>(GeneralValidator.Constraint.MIN, "8")
    );


    if (userService.findByUsername(user.getUsername()) != null) {
        bindingResult.reject(null, "username not unique");
    }

    if (userService.findByEmail(email) != null) {
        bindingResult.reject(null, "email not unique");
    }

    new GeneralValidator(constraints).invoke(user, bindingResult);






