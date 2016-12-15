Client MVC configuration
========================

Client MVC configuration represented at
`this <https://github.com/imCodePartnerAB/iVIS-Client-Sample/blob/master/src/main/java/com/imcode/configuration/ClientConfiguration.java>`_
file.

When invoking ModelAndView method setViewName next configuration concatenates it with viewPrefix and viewSuffix:

.. code-block:: java

    @Value("${spring.mvc.view.prefix}")
    private String viewPrefix;

    @Value("${spring.mvc.view.suffix}")
    private String viewSuffix;

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(viewPrefix);
        resolver.setSuffix(viewSuffix);
        return resolver;
    }

Methods for creating beans through ivisAuthorizedFilter() and ivisAuthorizedFilterRegistration() described
`here <http://docs.ivis.se/en/latest/sdk/routines/access_to_protected_resources.html>`_.

Initialize service class in handler methods:

.. code-block:: java

    @Bean
    public HandlerMethodArgumentResolver ivisServiceArgumentResolver() {
        return new IvisServiceArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(ivisServiceArgumentResolver());
    }

Converter that used for init Ivis entity object instead id property:

.. code-block:: java

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(ivisIdToDomainClassConverter());
    }

    @Bean
    public IvisIdToDomainClassConverter ivisIdToDomainClassConverter() {
        return new IvisIdToDomainClassConverter(conversionServiceFactoryBean().getObject());
    }

    @Bean
    public ConversionServiceFactoryBean conversionServiceFactoryBean() {
        return new ConversionServiceFactoryBean();
    }

Custom error views:

 .. code-block:: java

    @Bean
    public ServerProperties errorHandling() {
        return new ClientCustomization();
    }

Enable default configuration:

.. code-block:: java

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

Static content management:

.. code-block:: java

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/web-resources/");
    }