$(document).ready(function () {
    if ($('#restorePasswordFormEmail').length) {
        $('#restorePasswordFormEmail').validate({

            rules: {

                email: {
                    required: true,
                    email: true
                }

            },

            messages: {

                email: {
                    required: "Email is required",
                    email: "Email not valid"
                }

            }

        });
    }
    if ($('#restorePasswordFormDo').length) {

        $('#restorePasswordFormDo').validate({

            rules: {

                password: {
                    required: true,
                    minlength: 8
                },

                confirmPassword: {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
                }

            },

            messages: {

                password: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters"
                },

                confirmPassword: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters",
                    equalTo: "Password and confirmation do not match"
                }

            }

        });

    }
});