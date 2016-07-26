$(document).ready(function () {
    if ($('#registrationForm').length) {

        //name attribute watch
        $.validator.addMethod("checkUnique", function (value, element, param) {

            var flag = false;
            var propertyName = element.getAttribute("name");
            var data = {};
            data[propertyName] = value;
            $.ajax(
                {
                    url: param,
                    contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
                    type: "GET",
                    cache: false,
                    async: false,
                    data: data,
                    success: function (data) {
                        flag = data;
                    }
                }
            );

            return flag;

            }
        );



        $('#registrationForm').validate({

            rules: {

                username: {
                    required: true,
                    minlength: 4,
                    checkUnique: "/registration/usernameunique"
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
                    email: true,
                    checkUnique: "/registration/emailunique"
                },

                contactPhone: {
                    required: true,
                    minlenght: 8
                }

            },

            messages: {

                username: {
                    required: "Name is required",
                    minlength: "Minimum must be {0} characters",
                    checkUnique: "Username not unique"
                },

                password: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters"
                },

                confirmPassword: {
                    required: "Confirm password is required",
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
                    email: "Enter valid email",
                    checkUnique: "Email not unique"
                },

                contactPhone: {
                    required: "Contact phone is required",
                    minlength: "Minimum must be {0} characters"
                }

            }
        });
    }
});