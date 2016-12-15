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

All API services described at `ivis-services <https://github.com/imCodePartnerAB/iVIS/tree/master/ivis-services/src/main/java/com/imcode/services>`_ module.

:download:`Class diagram </images/services.png>` of all interfaces with methods.

.. note::
    Registered bean IvisServiceArgumentResolver provide you possibility use SDK in handler method next way. Also IvisIdToDomainClassConverter allow use id definition instead whole object.

    .. code-block:: java

        @RequestMapping(value = "/classes/save", method = RequestMethod.POST)
        public ModelAndView saveSchoolClass(@ModelAttribute("schoolClass") SchoolClass schoolClass,
                                            SchoolClassService classService) {
            //in this object pupils and school fields are objects with only one non null property id
            classService.save(schoolClass);
            view.setViewName("school_classes/created");
            return view;
        }




