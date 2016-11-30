$(document).ready(function () {
    if ($('#restorePasswordFormEmail').length) {

        //name attribute watch
        $.validator.addMethod("checkNotUnique", function (value, element, param) {

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

                return !flag;

            }
        );

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