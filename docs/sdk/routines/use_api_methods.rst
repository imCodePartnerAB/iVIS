Use api methods
===============

.. important::

    You can use API method only if admin check permission for that method for client and user (result it is a cross of permissions).

First you need get iVIS service factory from request.

.. code-block:: java

    HttpServletRequest request = ... ; // get HttpServletRequest
    IvisServiceFactory ivisServiceFactory = IvisOAuth2Utils.getServiceFactory(request);

.. note::

    If service factory invokes first time it wil create service factory in user session context.

Next uses exist IvisServiceFactory instance need get service for specific entity.

.. code-block:: java

    //for example you need ApplicationService
    ApplicationService applicationService = ivisServiceFactory.getService(ApplicationService.class);

And then just invoke API methods like Java methods.

.. code-block:: java

    List<Application> allApplications = applicationService.findAll();

.. tip::

    How to know what concrete method needs? Just see service interface methods: name, in parameters and return type.




