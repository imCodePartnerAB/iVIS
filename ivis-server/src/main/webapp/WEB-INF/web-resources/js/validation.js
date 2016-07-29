/**
 * Created by ruslan on 20.07.16.
 */
$(document).ready(function () {

    //enough default message
    $.validator.addMethod("selectMinOptions", function (value, element, param) {

            var counter = 0;
            $('#' + element.id + " option:visible").each(function (index, option) {
                if ($(option).is(':selected')) {
                    counter++;
                }
            });
            return counter >= param;

        },

        "Choose {0} or more options");

    //need write message manual
    $.validator.addMethod("checkboxesInspection", function (value, element, param) {
            var name = $(element).attr("name");
            var checkboxes = document.getElementsByName(name);
            var flag = true;
            $(checkboxes).each(function (index, checkbox) {
                var textOfCheckbox = $("label[for=" + checkbox.id + "]").text();
                if (param.indexOf(textOfCheckbox) != -1) {
                    if (!$(checkbox).is(":checked")) {

                        flag = false;
                        return flag;
                    }
                }
            });

            return flag;
        }
    );

    if ($('#clientUpdateForm').length) {

        $('#clientUpdateForm').validate({

                rules: {

                    name: {
                        required: true,
                        minlength: 4
                    },

                    resourceIds: {
                        required: true,
                        minlength: 4
                    },

                    clientSecret: {
                        required: true,
                        minlength: 4
                    },

                    scope: {
                        required: true,
                        checkboxesInspection: ["read", "write"]
                    },

                    authorizedGrantTypes: {
                        selectMinOptions: 1
                    },

                    registeredRedirectUri: {
                        required: true,
                        url: true
                    },

                    roles: {
                        required: true,
                        checkboxesInspection: ["ROLE_USER"]
                    },

                    accessTokenValiditySeconds: {
                        required: true,
                        number: true,
                        digits: true,
                        min: 90
                    },

                    refreshTokenValiditySeconds: {
                        required: true,
                        number: true,
                        digits: true,
                        min: 180
                    }

                },

                messages: {

                    name: {
                        required: "Name is required",
                        minlength: "Minimum must be {0} characters"
                    },

                    resourceIds: {
                        required: "Resources is required",
                        minlength: "Minimum must be {0} characters"
                    },

                    clientSecret: {
                        required: "Secret is required",
                        minlength: "Minimum must be {0} characters"
                    },

                    scope: {
                        required: "Scope is required",
                        checkboxesInspection: "Need choose read, write"
                    },

                    registeredRedirectUri: {
                        required: "Registered Redirect Uri is required",
                        url: "URL not valid"
                    },

                    roles: {
                        required: "Roles is required",
                        checkboxesInspection: "ROLE_USER need check"
                    },

                    accessTokenValiditySeconds: {
                        required: "Access Token Validity(sec) is required",
                        number: "Must be number",
                        digits: "Must be natural number",
                        min: "Minimum {0} seconds"
                    },

                    refreshTokenValiditySeconds: {
                        required: "Refresh Token Validity(sec) is required",
                        number: "Must be number",
                        digits: "Must be natural number",
                        min: "Minimum {0} seconds"
                    }

                },

                errorPlacement: function (error, element) {
                    if ($(element).is(":checkbox")) {
                        error.insertAfter(element.parent().parent())
                    } else {
                        error.insertAfter(element);
                    }
                }


            }
        );

        selectAndHideOptions("authorizedGrantTypes", ["refresh_token"]);
    }

    if ($('#userUpdateForm').length) {

        $("#password").val("");

        $('#userUpdateForm').validate({
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

                authorities: {
                    required: true,
                    checkboxesInspection: ["ROLE_USER"]
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
                    required: "Confirm password is required",
                    minlength: "Minimum must be {0} characters",
                    equalTo: "Password and confirmation do not match"
                },

                authorities: {
                    required: "Roles is required",
                    checkboxesInspection: "ROLE_USER must be chosen"
                }

            },

            errorPlacement: function (error, element) {
                if ($(element).is(":checkbox")) {
                    error.insertAfter(element.parent().parent())
                } else {
                    error.insertAfter(element);
                }
            },

            ignore: ":not(:visible)"
        });

    }

    if ($('#schoolUpdateForm').length) {

        $('#schoolUpdateForm').validate({
            rules: {

                name: {
                    required: true,
                    minlength: 4
                },

                schoolId: {
                    required: true,
                    minlength: 4
                },

                services: {
                    required: true
                }

            },

            messages: {

                name: {
                    required: "Name is required",
                    minlength: "Minimum must be {0} characters"
                },

                schoolId: {
                    required: "School ID is required",
                    minlength: "Minimum must be {0} characters"
                },

                services: {
                    required: "Services is required"
                }
            }

        });
    }

    if ($('#pupilUpdateForm').length) {

        $('#pupilUpdateForm').validate({

            rules: {

                "person.personalId": {
                    required: true,
                    minlength: 4
                },

                "person.lastName": {
                    required: true,
                    minlength: 4
                },

                "person.firstName": {
                    required: true,
                    minlength: 4
                }

            },

            messages: {

                "person.personalId": {
                    required: "Personal ID is required",
                    minlength: "Minimum must be {0} characters"
                },

                "person.lastName": {
                    required: "Last name is required",
                    minlength: "Minimum must be {0} characters"
                },

                "person.firstName": {
                    required: "First name is required",
                    minlength: "Minimum must be {0} characters"
                }

            }



        });
    }

    if ($("#userChangePasswordForm").length) {

        $.validator.addMethod("checkUserPassword", function (value, element, param) {

            var flag = false;
            var data = {};
            var link = $("#userChangePasswordForm").attr("action");
            data["checkpassword"] = value;
            $.ajax(
                {
                    url: link.substring(0, link.indexOf("?")),
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

        $('#userChangePasswordForm').validate({

            rules: {

                oldPassword: {
                    required: true,
                    minlength: 8,
                    checkUserPassword: true
                },

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

                oldPassword: {
                    required: "Old password is required",
                    minlength: "Minimum must be {0} characters",
                    checkUserPassword: "Old password does not match"
                },

                password: {
                    required: "Password is required",
                    minlength: "Minimum must be {0} characters"
                },

                confirmPassword: {
                    required: "Confirm password is required",
                    minlength: "Minimum must be {0} characters",
                    equalTo: "Password and confirmation do not match"
                }

            }

        });
    }
});

function selectAndHideOptions(idSelect, optionValues) {

    $('#' + idSelect + " option").each(function (index, option) {
        var $option = $(option);
        if (optionValues.indexOf($option.val()) != -1) {
            $option.attr("selected", "selected");
            $option.hide();
        }

    })

}



