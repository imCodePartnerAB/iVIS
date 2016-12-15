Client customization
====================

Client customization represented at
`this <https://github.com/imCodePartnerAB/iVIS-Client-Sample/blob/master/src/main/java/com/imcode/configuration/ClientCustomization.java>`_
file.

Purpose of this configuration is create custom error views on which servlet container will be redirect when exception will occurred.

The main thing is that need process two types of errors from IvisAuthorizedFilter:

1. org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException.

2. org.springframework.security.access.AccessDeniedException.

