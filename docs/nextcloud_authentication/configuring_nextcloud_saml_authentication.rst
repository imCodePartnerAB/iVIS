Configuration
=============

Here configuration is presented for Nextcloud 11, for the latest versions configuration is the same.

1. First of all, install Nextcloud server on your computer.

2. Enable app "SSO & SAML authentication":

    2.1 Go to "Apps", in menu select "Not enabled" and enable app "SSO & SAML authentication".

3. Configure app "SSO & SAML authentication":

    3.1 Go to "Admin", in menu select "SSO & SAML authentication" and then select "Use built-in SAML authentication" option;

    3.2 Fill corresponding fields (example on image below):

        "Attribute to map the UID to." -> username

        "Identifier of the IdP entity (must be a URI)" -> http://ivis-saml-idp

        "URL Target of the Idp where the SP will send the Authentication Request Message" -> http://localhost:8080/idp/SingleSignOnService

        "URL Location of the IdP where the SP will send the SLO Request" -> http://localhost:8080/idp/SingleLogoutService

        "Public X.509 certificate of the IdP" ->

            -----BEGIN CERTIFICATE-----
                    certificate value
            -----END CERTIFICATE-----

.. image:: /images/nextcloudSamlConfig.png

.. note::
Here "http://localhost:8080" is host of iVIS server.