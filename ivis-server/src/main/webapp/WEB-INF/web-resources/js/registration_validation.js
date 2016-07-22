$(document).ready(function () {
    if ($('#registrationForm').length) {
        $('#registrationForm').validate({

            rules: {

                username: {
                    required: true,
                    minlength: 4
                },

                password: {
                    required: true,
                    minlength: 8
                },

                confirmPassword: {
                    required: true,
                    minlength: 8,
                    equalTo: "#password"
                },

                firstName: {
                    required: true,
                    minlength: 4
                },

                lastName: {
                    required: true,
                    minlength: 4
                },

                email: {
                    required: true,
                    email: true
                },

                contactPhone: {
                    required: true,
                    minlenght: 8
                }

            },

            messages: {

                username: {
                    required: "Name is required",
                    minlength: "Minimum must be {0} characters"
                },

                password: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters"
                },

                confirmPassword: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters",
                    equalTo: "Password and confirmation do not match"
                },

                firstName: {
                    required: "First name is required",
                    minlength: "Minimum must be {0} characters"
                },

                lastName: {
                    required: "Last name is required",
                    minlength: "Minimum must be {0} characters"
                },

                email: {
                    required: "Email is required",
                    email: "Enter valid email"
                },

                contactPhone: {
                    required: "Contact phone is required",
                    minlength: "Minimum must be {0} characters"
                }

            }
        });
    }
});